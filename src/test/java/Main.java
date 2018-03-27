import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        Workbook wb = WorkbookFactory.create(
                new File("C:\\Users\\Vladyslav_Nahaiev\\Desktop" +
                        "\\HtmlConverter\\uploads\\test1.xlsx"));

        wb.getAllNames().forEach(System.out::print);

    }
}
