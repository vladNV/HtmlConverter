package model.service;

import model.parser.ExcelStream;
import model.parser.PdfStream;
import model.repo.UnitWorkRepo;

import java.util.ArrayList;
import java.util.List;

public class ConvertPdfService {

    public List<String> convertPdf(UnitWorkRepo repo,
                                   int sheet, int column) {
        String path = (String) repo.pull("path");
        List<String> lines = new ArrayList<>();
        try (ExcelStream stream = new ExcelStream()){
            lines = stream
                    .setPath(path)
                    .openbook()
                    .sheet(sheet)
                    .getValueFrom(column);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PdfStream stream = new PdfStream()){
                stream
                    .setPath(path)
                    .createPdf()
                    .opendocument()
                    .writeLines(lines);
            repo.push("pdf", path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

}
