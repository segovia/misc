package segovia.cube_3d;

public class Canvas {

    private String[][] canvas;

    public Canvas(int rowsOfSquares, int colsOfSquares) {
        int rows = rowsOfSquares * 6 + 1;
        int cols = colsOfSquares * 6 + 1;
        canvas = new String[rows][cols];
        for (int i = 0; i < cols; i++) {
            canvas[0][i] = "--";
            canvas[rows - 1][i] = "--";
        }
        for (int i = 0; i < rows; i++) {
            canvas[i][0] = "| ";
            canvas[i][cols - 1] = " |";
        }
        canvas[0][0] = "*-";
        canvas[0][cols - 1] = "-*";
        canvas[rows - 1][0] = "*-";
        canvas[rows - 1][cols - 1] = "-*";
    }

    public void write(Square s, int orientation, int squareIOffset, int squareJOffset) {
        s.writeOnStringMatrix(orientation, 1 + squareIOffset * 6, 1 + squareJOffset * 6, canvas);
    }

    @Override
    public String toString() {
        return stringMatrixToString(canvas);
    }

    public static String stringMatrixToString(String[][] canvas) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                String str = canvas[i][j];
                if (str == null) {
                    sb.append("  ");
                } else {
                    sb.append(str);
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
