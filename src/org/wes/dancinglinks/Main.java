package org.wes.dancinglinks;

/**
 * A test of the Dancing links algorithm
 */
public class Main {

    public static void main(String[] args) throws Exception {

        int[][] matrix = createMatrix();

        Column column = new Column(matrix);

        demonstrateIterable(column);

        printResults(matrix, column);
    }

    private static void demonstrateIterable(Column column) {
        // you can already iterate over a Column like this
        for(Node n : column) {
            ((Column) n).getSize();
        }
    }

    private static void printResults(int[][] matrix, Column column) {
        System.out.println('\n' + visualizeMatrix(matrix));
        System.out.println("Column:\n" + column);
        System.out.println("Nodes:");
        System.out.println(column.getL().getD());
        System.out.println(column.getR().getU());
        System.out.println(column.getR().getR().getU());

        System.out.println(column.getR().getClass());
        System.out.println(column.getL().getD().getClass());
    }

    private static int[][] createMatrix() {
        int[][] matrix = new int[8][7];

        matrix[0] = new int[]{0, 0, 1, 0, 1, 1, 0};
        matrix[1] = new int[]{1, 0, 0, 1, 0, 0, 0};
        matrix[2] = new int[]{0, 1, 1, 0, 0, 1, 0};
        matrix[3] = new int[]{0, 0, 0, 1, 0, 1, 1};
        matrix[4] = new int[]{0, 1, 0, 0, 0, 0, 1};
        matrix[5] = new int[]{0, 0, 0, 1, 1, 0, 1};
        matrix[6] = new int[]{0, 0, 0, 0, 0, 0, 1};
        matrix[7] = new int[]{0, 0, 0, 0, 0, 1, 1};
        return matrix;
    }

    private static String visualizeMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
