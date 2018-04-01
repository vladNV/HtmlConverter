package model.parser;

import java.util.ArrayList;
import java.util.List;


public class Designer {
    private final static String cert = "regexp_certificate_num";
    private final static String calibrate = "regexp_calibrate_date";
    private final static String calibrated = "regexp_item_calibrated";
    private final static String customer = "regexp_customer";
    private final static String method = "regexp_calibration_method";
    private final static String name = "regexp_customer_name";
    private final static String issue = "regexp_data_issue";
    private final static String conditions = "regexp_calibration_conditions";
    private final static String additional = "regexp_additional_information";
    private final static String using1 = "regexp_calibration_performed_1";
    private final static String using2 = "regexp_calibration_performed_2";



    public static void main(String[] args) throws Exception {

        PdfStream stream = new PdfStream();
        stream.close();
    }

    private static List<String> converter(List<String> bug) {
        List<String> a = new ArrayList<>();
        bug.forEach(v -> a.add(String.format(v, "UTF-8")));
        return a;
    }

}
