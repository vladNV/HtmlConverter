package model.rest;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import controller.context.ApplicationContext;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.UriBuilder;
import model.repo.UnitWorkRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.ResourceBundle;

@Path("/upload")
public class FileUploadService {
    private static final String UPLOAD_FOLDER;
    private static final int BATCH_SIZE = 1024;
    private static final String PATH_CONTEXT;
    private static final int SIZE = 32;

    static {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("config");
            UPLOAD_FOLDER = bundle.getString("upload-folder");
            PATH_CONTEXT = bundle.getString("path-context");
        } catch (Throwable e) {
            throw new RuntimeException("exception at config loading", e);
        }
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadExcelFile(
            @FormDataParam("login") String login,
            @FormDataParam("xls") InputStream uploadedInputStream,
            @FormDataParam("xls") FormDataContentDisposition content)
            throws URISyntaxException {
        if (login == null) {
            return Response.status(400).entity("Invalid login").build();
        }
        if (uploadedInputStream == null || content == null) {
            return Response.status(400).entity("Invalid form data").build();
        }
        try {
            createFolderIfNotExist(UPLOAD_FOLDER);
        } catch (SecurityException e) {
            return Response.status(500)
                    .entity("Can not create destination folder on server")
                    .build();
        }
        String uploadedFileLocation = UPLOAD_FOLDER + "\\" +
                System.currentTimeMillis() + ".xlsx";
        try {
            saveToFile(uploadedInputStream, uploadedFileLocation);
            uploadedInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setPathToUnitWork(login, uploadedFileLocation);
        URI uri = new URI(PATH_CONTEXT + controller.command.Path.OPEN_EXCEL_URI);
        return Response.seeOther(uri).build();
    }

    private void setPathToUnitWork(String login, String path) {
        ApplicationContext app = ApplicationContext.getInstance();
        HashMap<String, UnitWorkRepo> uwr =
        (HashMap<String, UnitWorkRepo>) app.getBean("repos");
            uwr
                .compute(login, (k,v) -> new UnitWorkRepo())
                .push("path", path);
    }

    private void saveToFile(InputStream stream, String target)
            throws IOException {
        int read;
        byte[] bytes = new byte[BATCH_SIZE];
        try (OutputStream out = new FileOutputStream(new File(target))) {
            while ((read = stream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException e) {
            throw new RuntimeException("was thrown just in time reading " +
                    "from input xls stream", e);
        }
    }

    private void createFolderIfNotExist(String dirName) throws SecurityException {
        File fileDir = new File(dirName);
        if (!fileDir.exists()) fileDir.mkdir();
    }

}
