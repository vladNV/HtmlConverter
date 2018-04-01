package model.parser;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;

import com.itextpdf.tool.xml.XMLWorkerHelper;


import java.io.*;
import java.util.List;

public class PdfStream implements AutoCloseable {
    private final static int MAX_FONT_SIZE = 32;
    private static final String FONT = "C:\\Windows\\Fonts\\Times New Roman.ttf";

    private String path;
    private Document document;
    private Font font;
    private Font font2;
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

    // need to change font path
    public PdfStream createPdf() {
        try {
            BaseFont bf = BaseFont.createFont
                    ("C:\\Windows\\Fonts\\arial.ttf",
                            BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            font = new Font(bf, 14);
            font2 = new Font(bf, 14);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return this;
    }


    public void writeAsTable(String[][] lines) {
        StringBuilder content = new StringBuilder("\n\n<html><body><table border=\"1\" cellpadding=\"15\">");
        // here will be table header
        for (String[] row : lines) {
            content.append("<tr>");
            for (String cell : row) {
                content.append("<td>");
                content.append(cell);
                content.append("</td>");
            }
            content.append("</tr>");
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

    public void write(List<String> text, String[][] table) throws DocumentException {

        font.setSize(20);
        font.setColor(BaseColor.BLUE);
        font.setStyle(Font.BOLD);
        Paragraph p = new Paragraph("НАЦИОНАЛЬНЫЙ ИНСТИТУТ МЕТРОЛОГИИ", font);
        document.add(p);

        font.setSize(16);
        font.setColor(BaseColor.BLUE);
        font.setStyle(Font.NORMAL);
        Paragraph p1 = new Paragraph("National metrological institute", font);
        p1.setAlignment(Element.ALIGN_CENTER);
        document.add(p1);

        document.add(Chunk.NEWLINE);

        font.setColor(BaseColor.BLACK);
        font.setStyle(Font.BOLD);
        font.setSize(24);
        Paragraph p2 = new Paragraph("Сертификат калибровки", font);
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);

        font.setStyle(Font.NORMAL);
        font.setSize(16);
        Paragraph p3 = new Paragraph("Calibration certificate", font);
        p3.setAlignment(Element.ALIGN_CENTER);
        document.add(p3);

        document.add(Chunk.NEWLINE);

        font.setSize(12);
        //add 1-2 text here
        Paragraph p4 = new Paragraph("Номер сертификата " + text.get(0) +
                "  Дата калибровки  " + text.get(1) + " " +
                "Страница  1  из  2", font);
        document.add(p4);

        font.setSize(10);
        Paragraph p5 = new Paragraph("Certificate number       ____________   " +
                "Date when celebrated" +
                "      ____________   Page      ____ of ____", font);
        document.add(p5);

        document.add(Chunk.NEWLINE);

        document.add(Chunk.NEWLINE);

        PdfPTable t1 = new PdfPTable(new float[]{250f,750f});
        t1.setWidthPercentage(100);

        PdfPCell c1 = new PdfPCell();
        font.setSize(12);
        c1.addElement(new Phrase("Объект калибровки", font));
        font2.setSize(8);
        c1.addElement(new Phrase("Item calibrated", font2));
        c1.setBorder(0);
        t1.addCell(c1);

        PdfPCell c2 = new PdfPCell();
        Chunk ch1 = new Chunk(text.get(2), font);
        ch1.setUnderline(0.2f, -2f);
        c2.addElement(ch1);
        font.setSize(8);
        font.setColor(BaseColor.BLUE);
        Phrase ph1 = new Phrase("Наименование эталона / " +
                "средства измерения / идентификация", font);
        ph1.add(Chunk.NEWLINE);
        ph1.add("Description of measurement standard / measuring instrument / identification");
        c2.addElement(ph1);
        c2.setBorder(0);
        t1.addCell(c2);

        PdfPCell c3 = new PdfPCell();
        font.setSize(12);
        font.setColor(BaseColor.BLACK);
        c3.addElement(new Phrase("Заказчик", font));
        font2.setSize(8);
        c3.addElement(new Phrase("Customer", font2));
        c3.setBorder(0);
        t1.addCell(c3);

        PdfPCell c4 = new PdfPCell();
        ch1 = new Chunk(text.get(3), font);
        ch1.setUnderline(0.2f, -2f);
        c4.addElement(ch1);
        font.setSize(8);
        font.setColor(BaseColor.BLACK);
        font2.setColor(BaseColor.BLUE);
        ph1 = new Phrase("Информация о заказчике, адрес", font2);
        ph1.add(Chunk.NEWLINE);
        ph1.add("Name of the customer, address");
        c4.addElement(ph1);
        c4.setBorder(0);
        t1.addCell(c4);

        PdfPCell c5 = new PdfPCell();
        font.setSize(12);

        c5.addElement(new Phrase("Метод калибровки", font));
        font2.setSize(8);
        font2.setColor(BaseColor.BLACK);
        c5.addElement(new Phrase("Method of calibration", font2));
        c5.setBorder(0);
        t1.addCell(c5);

        PdfPCell c6 = new PdfPCell();
        ch1 = new Chunk(text.get(4), font);
        ch1.setUnderline(0.2f, -2f);
        c6.addElement(ch1);
        font.setSize(8);
        font.setColor(BaseColor.BLUE);
        ph1 = new Phrase("Наименнование метода / идентификация", font);
        ph1.add(Chunk.NEWLINE);
        ph1.add("Name of the method / identification");
        c6.addElement(ph1);
        c6.setBorder(0);
        t1.addCell(c6);
        document.add(t1);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);


        font.setSize(8);
        font.setColor(
                BaseColor.BLACK
        );
        font.setStyle(Font.ITALIC);
        Chunk under1 = new Chunk("                                                                                                              " +
                "                                             ");
        under1.setUnderline(1f, -2.0f);
        document.add(under1);
        document.add(Chunk.NEWLINE);
        Paragraph p6 = new Paragraph("Все измерения имеют прослеживаемость к единицам Международной системы SI, которые воспроизводятся национальными эталонам НМИ. В" +
                "сертификате приведены результаты калибровки согласующиеся с возможностями, содержащимися в Приложении С соглашения MRA, разработанном" +
                "МКМВ. В рамках MRA все участвующие НМИ взаимно признают действительность своих сертификатов калибровки и измерений в отношении" +
                "измеренных значений, диапазонов и неопределенностей измерений, указанных в Приложении С (подробности см. http://www.bipm.org). Данный" +
                "сертификат может быть воспроизведен только полностью. Любая публикация или частичное воспроизведение содержания сертификата возможны с" +
                "письменного разрешения НМИ, выдавшего сертификат.", font);
        Paragraph p7 = new Paragraph("All measurements are traceable to the SI units which are realized by national measurement standards of NMI. This certificate is consistent with the capabilities that" +
                "are included in Appendix C of the MRA drawn up by the CIPM. Under the MRA, all participating NMIs recognize the validity of each other's calibration and" +
                "measurement certificates for the quantities, ranges and measurement uncertainties specified in Appendix C (for details see http://www.bipm.org). This certificate" +
                "shall not be reproduced, except in full. Any publication extracts from the calibration certificate requires written approval of the issuing NMI.", font);
        document.add(Chunk.NEWLINE);
        document.add(p6);
        document.add(p7);
        document.add(Chunk.NEWLINE);
        document.add(under1);
        document.add(Chunk.NEWLINE);

        t1 = new PdfPTable(new float[]{400f,100f,600f,250f,200f});
        t1.setWidthPercentage(100);

        font.setStyle(Font.NORMAL);
        font2.setStyle(Font.NORMAL);

        c1 = new PdfPCell();
        font.setSize(11);
        font.setColor(BaseColor.BLACK);
        c1.addElement(new Phrase("Утверждающая подпись", font));
        font2.setSize(8);
        c1.addElement(new Phrase("Authorising signature", font2));

        c2 = new PdfPCell();
        Paragraph u1 = new Paragraph(new Chunk("              "));
        ch1.setUnderline(0.2f, -2.0f);
        c2.addElement(u1);

        c3 = new PdfPCell();
        c3.addElement(new Phrase(text.get(5), font));

        Chunk ch2 = new Chunk("                                         ");
        ch2.setUnderline(0.2f, -2);
        c3.addElement(ch2);
        c3.addElement(new Phrase("Ф.И.О и должность / Name and function", font2));

        c4 = new PdfPCell();
        c4.addElement(new Phrase("Дата выдачи", font));
        c4.addElement(new Phrase("Date of issue", font2));

        c5 = new PdfPCell();
        c5.addElement(new Phrase(text.get(6), font));
        Chunk ch3 = new Chunk("                     ");
        ch3.setUnderline(0.2f, -2);
        c5.addElement(ch3);

        c1.setBorder(0);
        c2.setBorder(0);
        c3.setBorder(0);
        c4.setBorder(0);
        c5.setBorder(0);

        t1.addCell(c1);
        t1.addCell(c2);
        t1.addCell(c3);
        t1.addCell(c4);
        t1.addCell(c5);

        document.add(t1);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(under1);
        font.setSize(8);
        font.setColor(BaseColor.BLUE);
        Paragraph p8 = new Paragraph("Адрес НМИ / Address of NMI / " +
                "Телефон, факс, е-почта, web- сайт / Phone, fax, e-mail, website", font);
        document.add(p8);
        document.newPage();

        font.setSize(13);
        font.setStyle(Font.BOLD);
        font.setColor(BaseColor.BLACK);
        Paragraph p9 = new Paragraph("Сертификат калибровки", font);
        document.add(p9);

        font2.setSize(10);
        Paragraph p10 = new Paragraph("Calibration certificate", font2);
        document.add(p10);

        font.setStyle(Font.NORMAL);
        PdfPTable t2 = new PdfPTable(new float[]{750f, 250f});
        t2.setWidthPercentage(100);
        c1 = new PdfPCell();
        c1.addElement(new Phrase("Номер сертификата  " + text.get(0), font));

        font2.setStyle(Font.NORMAL);
        Phrase ph2 = new Phrase();
        ph2.add(new Phrase("Certificate number ______________________________", font2));
        Chunk ch4 = new Chunk("                                   ");
        ch4.setUnderline(0.2f,-2);
        ph2.add(ch4);
        c1.addElement(ph2);
        c1.addElement(Chunk.NEWLINE);

        c2 = new PdfPCell();
        c2.addElement(new Phrase("Страница 2 из 2",font2));
        c2.addElement(new Phrase("Page ____ of ____",font2));

        document.add(Chunk.NEWLINE);
        t2.addCell(c1);
        t2.addCell(c2);
        document.add(t2);
        c1.addElement(Chunk.NEWLINE);

        PdfPTable t3 = new PdfPTable(new float[]{300f, 700f});
        t3.setWidthPercentage(100);

        PdfPCell c7 = new PdfPCell();
        font.setSize(12);
        c7.addElement(new Phrase("Калибровка выполнена с " +
                "помощью", font));
        font2.setSize(8);
        c7.addElement(new Phrase("Calibration is performed by using", font2));
        c7.setBorder(0);
        t3.addCell(c7);

        PdfPCell c8 = new PdfPCell();
        Chunk ch5 = new Chunk(text.get(7), font);
        ch5.setUnderline(0.2f, -2f);
        c8.addElement(ch5);
        font2.setSize(8);
        font2.setColor(BaseColor.BLUE);
        Phrase ph3 = new Phrase("Наименование эталонов и их статус / идентификация " +
                "/ доказательство прослеживаемости", font2);
        ph3.add(Chunk.NEWLINE);
        ph3.add(new Phrase("Description of the reference measurement standards / " +
                "identification / evidence of traceability", font2));
        c8.addElement(ph3);
        c8.setBorder(0);
        t3.addCell(c8);

        PdfPCell c9 = new PdfPCell();
        font.setSize(12);
        c9.addElement(new Phrase("Условия калибровки", font));
        font2.setSize(8);
        font2.setColor(BaseColor.BLACK);
        c9.addElement(new Phrase("Calibration conditions", font2));
        c9.setBorder(0);
        t3.addCell(c9);

        PdfPCell c10 = new PdfPCell();
        Chunk ch6 = new Chunk(text.get(8), font);
        ch6.setUnderline(0.2f, -2f);
        c10.addElement(ch6);
        font.setSize(8);
        font.setColor(BaseColor.BLUE);
        Phrase ph4 = new Phrase("Условия окружающей среды и другие влияющие факторы", font);
        ph4.add(Chunk.NEWLINE);
        ph4.add("Environmental conditions and other influence parameters");
        c10.addElement(ph4);
        c10.setBorder(0);
        t3.addCell(c10);
        ph4.add(Chunk.NEWLINE);

        document.add(t3);

        font.setSize(12);
        font.setColor(BaseColor.BLACK);
        Paragraph p11 = new Paragraph("Результаты калибровки, " +
                "включая неопределенность",font);
        document.add(p11);

        font.setSize(8);
        Paragraph p12 = new Paragraph("Calibration results " +
                "including uncertainty", font);
        document.add(p12);
        document.add(Chunk.NEWLINE);

        PdfPTable t4 = new PdfPTable(new float[]{100f, 250f, 250f, 250f, 250f});
        t4.setWidthPercentage(100);

        PdfPCell c11 = new PdfPCell();
        PdfPCell c12 = new PdfPCell();
        PdfPCell c13 = new PdfPCell();
        PdfPCell c14 = new PdfPCell();
        PdfPCell c15 = new PdfPCell();

        font.setSize(12);
        font.setStyle(Font.BOLD);
        c11.addElement(new Phrase("   Гц", font));
        c12.addElement(new Phrase("Уровень\n" +
                "чувствительности*,\n" +
                "дБ отн. 1В/Па\n\n", font));
        c13.addElement(new Phrase("Расширенная\n" +
                "неопределенность,\n" +
                "дБ\n\n", font));
        c14.addElement(new Phrase("Коэффициент\n" +
                "давления, дБ/кПа\n\n",font));
        c15.addElement(new Phrase("Температурный\n" +
                "коэффициент, дБ/оС\n\n",font));
        t4.addCell(c11);
        t4.addCell(c12);
        t4.addCell(c13);
        t4.addCell(c14);
        t4.addCell(c15);
        PdfPCell pCell;
        for (String[] row : table) {
            for (String cell : row) {
                pCell = new PdfPCell(new Phrase(cell));
                t4.addCell(pCell);
            }
        }
        document.add(t4);
        document.add(under1);
        document.add(Chunk.NEWLINE);

        font.setStyle(Font.ITALIC);
        font.setSize(8);
        Paragraph p13 = new Paragraph(
                "Расширенная неопределенность получена путем умножения стандартной неопределенности на коэффициент охвата k = 2, соответствующего уровню" +
                        "доверия приблизительно равному 95 % при допущении нормального распределения. Оценивание неопределенности проведено в соответствии с" +
                        "«Руководством по выражению неопределенности измерений» (GUM)." +
                        "The expanded uncertainty is obtained by multiplying the combined standard uncertainty by a coverage factor k = 2 corresponding to a confidence interval of" +
                        "approximately 95 % assuming a normal distribution. The evaluation of uncertainty is conducted according to the “Guide to the expression of uncertainty in" +
                        "measurement” (GUM)", font
        );

        document.add(p13);
        document.add(under1);
        document.add(Chunk.NEWLINE);

        PdfPTable t5 = new PdfPTable(new float[]{400f, 600f});
        t5.setWidthPercentage(100);

        font.setStyle(Font.NORMAL);
        PdfPCell c16 = new PdfPCell();
        font.setSize(12);
        c16.addElement(new Phrase("Дополнительная информация", font));
        font2.setSize(8);
        font2.setColor(BaseColor.BLACK);
        c16.addElement(new Phrase("Additional information", font2));
        c16.setBorder(0);
        t5.addCell(c16);

        PdfPCell c17 = new PdfPCell();
        Chunk ch7 = new Chunk(text.get(9), font);
        ch7.setUnderline(0.2f, -2f);
        c17.addElement(ch7);
        font.setSize(8);
        font.setColor(BaseColor.BLUE);
        Phrase ph5 = new Phrase("состояние объекта калибровки / регулировка и/или " +
                "ремонт объекта калибровки до его калибровки /\n" +
                "рекомендуемый межкалибровочный интервал по требованию заказчика", font);
        ph5.add(Chunk.NEWLINE);
        ph5.add("condition of the item of calibration / adjustments or repair of " +
                "the item of calibration before calibrated /\n" +
                "recommended recalibration period, if requested by the customer");
        c17.addElement(ph5);
        c17.setBorder(0);
        t5.addCell(c17);
        document.add(t5);

        document.add(Chunk.NEWLINE);

        font.setSize(13);
        font.setColor(BaseColor.BLACK);
        Paragraph p14 = new Paragraph("Подпись лица, выполнившего калибровку              " +
                text.get(10), font);
        document.add(p14);
        document.add(Chunk.NEWLINE);

        font2.setSize(8);
        Paragraph p15 = new Paragraph("Signature of the person " +
                "who has performed calibration         ",font2);
        Chunk ch8 = new Chunk("                    ", font2);
        ch8.setUnderline(0.2f, -2f);
        p15.add(ch8);
        p15.add("                      ");
        Chunk ch9 = new Chunk("                                               " +
                "                          ", font2);
        ch9.setUnderline(0.2f, -2f);
        p15.add(ch9);
        document.add(p15);

    }

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
