package model.service;

import model.parser.ExcelStream;
import model.parser.PdfStream;
import model.repo.UnitWorkRepo;
import model.util.MathHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConvertPdfService {
    private static final ResourceBundle resource;

    private static final int SHEET_LIST;
    private static final int SHEET_TABLE;
    private static final int COLUMN_LIST_0;
    private static final int COLUMN_TABLE_0;
    private static final int COLUMN_TABLE_1;
    private static final int COLUMN_TABLE_2;
    private static final int COLUMN_TABLE_3;
    private static final int COLUMN_SIZE = 4;

    static {
        resource = ResourceBundle.getBundle("config");
        SHEET_LIST = Integer.parseInt(resource.getString("sheet-list"));
        SHEET_TABLE = Integer.parseInt(resource.getString("sheet-table"));
        COLUMN_LIST_0 = Integer.parseInt(resource.getString("column-list"));
        COLUMN_TABLE_0 = Integer.parseInt(resource.getString("column-table-0"));
        COLUMN_TABLE_1 = Integer.parseInt(resource.getString("column-table-1"));
        COLUMN_TABLE_2 = Integer.parseInt(resource.getString("column-table-2"));
        COLUMN_TABLE_3 = Integer.parseInt(resource.getString("column-table-3"));
    }

    public List<String> convertPdf(UnitWorkRepo repo) {
        String path = (String) repo.pull("path");
        List<String> lines = new ArrayList<>();
        String[][] table = new String[COLUMN_SIZE][];
        try (ExcelStream stream = new ExcelStream()){
            lines = stream
                    .setPath(path)
                    .openbook()
                    .sheet(SHEET_LIST)
                    .getValueFrom(COLUMN_LIST_0);
            table[0] = stream
                    .sheet(SHEET_TABLE)
                    .getValueFrom(COLUMN_TABLE_0).toArray(new String[0]);
            table[1] = stream
                    .sheet(SHEET_TABLE)
                    .getValueFrom(COLUMN_TABLE_1).toArray(new String[0]);
            table[2] = stream
                    .sheet(SHEET_TABLE)
                    .getValueFrom(COLUMN_TABLE_2).toArray(new String[0]);
            table[3] = stream
                    .sheet(SHEET_TABLE)
                    .getValueFrom(COLUMN_TABLE_3).toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        path = path.replaceAll("(xlsx|xls)", "pdf");
        try (PdfStream stream = new PdfStream()){
                stream
                    .setPath(path)
                    .createPdf()
                    .opendocument()
                    .writeLines(lines);
            table = MathHelper.transport(table);
                stream.writeAsTable(table);
            repo.push("pdf", path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}
