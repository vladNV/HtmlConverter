package model.parser;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelStream implements AutoCloseable {
    private String path;
    private Workbook workbook;
    private Sheet sheet;

    public ExcelStream() {}

    public ExcelStream(String path, Workbook workbook) {
        this.path = path;
        this.workbook = workbook;
    }


    public ExcelStream setPath(String path) {
        if (path == null || path.isEmpty())
            throw new NullPointerException("path cannot be null or empty");
        this.path = path;
        return this;
    }

    public ExcelStream openbook() {
        if (path == null || path.isEmpty())
            throw new NullPointerException("path cannot be null or empty");
        try (FileInputStream fis = new FileInputStream(new File(path))){
            workbook = WorkbookFactory.create(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return this;
    }

    public ExcelStream sheet(int at) {
        if (workbook == null)
            throw new NullPointerException("woorbook cannot be null");
        this.sheet = workbook.getSheetAt(at);
        return this;
    }

    public List<String> getValueFrom(int columnNumber, char separator) {
        List<String> values = new ArrayList<>();
        Cell cell;
        for (Row row : sheet) {
            cell = row.getCell(columnNumber);
            if (cell != null) {
                values.add(cell.toString().trim() + separator);
            }
        }
        return values;
    }

    public List<String> getValueFrom(int columnNumber) {
        List<String> values = new ArrayList<>();
        Cell cell;
        for (Row row : sheet) {
            cell = row.getCell(columnNumber);
            if (cell != null) {
                values.add(cell.toString().trim());
            }
        }
        return values;
    }


    @Override
    public void close() throws Exception {
        if (workbook != null) {
            workbook.close();
        } else {
            throw new RuntimeException("excel parser wasn't closed, exception");
        }
    }
}
