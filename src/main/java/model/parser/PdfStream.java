package model.parser;

import com.itextpdf.text.*;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;


import java.io.*;
import java.util.List;

public class PdfStream implements AutoCloseable {
    private final static int MAX_FONT_SIZE = 32;

    private String path;
    private Document document;
    private Font font;
    private PdfWriter writer;

    public String getPath() {
        return path;
    }

    public PdfStream setPath(String path) {
        if (path == null || path.isEmpty())
            throw new NullPointerException("path cannot be null or empty");
        this.path = path;
        return this;
    }

    public PdfStream opendocument() {
        document = new Document(PageSize.A4);
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            writer.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
            document.open();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }

    // need to change font's path
    public PdfStream createPdf() {
        try {
            BaseFont bf = BaseFont.createFont
                    ("C:\\Windows\\Fonts\\arial.ttf",
                            BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            font = new Font(bf, 14);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return this;
    }


    public void writeAsTable(List<String> lines) {
        StringBuilder content = new StringBuilder("<html><body><table>");
        // here will be table header
        for (String row : lines) {
            content.append("<tr>");
            for (String cell : row.split(";")) {
                content.append("<td>");
                content.append(cell);
                content.append("</td>");
            }
            content.append("<tr>");
        }
        content.append("</table></body></html>");
        InputStream is = new ByteArrayInputStream
                (content.toString().getBytes());

        try {
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // https://docraptor.com/documentation/java

    public void writeLines(List<String> lines) {
        Paragraph paragraph;
        try {
            for (String line : lines) {
                paragraph = new Paragraph(String.format(line + "\n", "UTF-8"),
                        font);
                paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(paragraph);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (document != null && writer != null) {
            document.close();
            writer.close();
        } else {
            throw new RuntimeException("pdf parser wasn't closed, exception");
        }
    }
}
