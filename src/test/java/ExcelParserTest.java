import model.parser.ExcelStream;
import model.parser.PdfStream;
import org.junit.Test;

import java.util.List;

public class ExcelParserTest {

    @Test
    public void testParserExcel() {
        List<String> list = null;
        try (ExcelStream e = new ExcelStream()) {
            list =
                    e.setPath("data\\pattern.xls").openbook().sheet(0).getValueFrom(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PdfStream pdfParser = new PdfStream()){
            pdfParser.setPath("data\\new.pdf").createPdf().opendocument().writeLines(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
