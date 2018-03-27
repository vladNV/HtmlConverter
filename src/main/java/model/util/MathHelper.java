package model.util;

public class MathHelper {

    public static String[][] transport(String[][] matrix) {
        String[][] m = new String[matrix[0].length][matrix.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                m[i][j] = matrix[j][i];
            }
        }
        return m;
    }


}
