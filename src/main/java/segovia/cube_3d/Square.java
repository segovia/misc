package segovia.cube_3d;

import static segovia.cube_3d.Canvas.stringMatrixToString;

public class Square {
    private final boolean[] sides;

    public Square(String s) {
        sides = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            sides[i] = s.charAt(i) == 'x';
        }
    }

    public boolean matches(int thisSide, int thisOrientation, Square o, int otherSide, int otherOrientation) {
        boolean thisInv = thisOrientation >= 4;
        boolean otherInv = otherOrientation >= 4;
        int thisSideStart = ((4 + (thisInv ? -thisSide : thisSide) + thisOrientation) % 4) * 4 + 16;
        int otherSideStart = ((4 + (otherInv ? -otherSide : otherSide) + otherOrientation) % 4) * 4 + 16;
        for (int i = 0; i < 5; i++) {
            boolean thisBump = sides[(thisSideStart + (thisInv ? 4 - i : i)) % 16];
            boolean otherBump = o.sides[(otherSideStart + (otherInv ? i : 4 - i)) % 16];
            if (thisBump && otherBump || i > 0 && i < 4 && thisBump == otherBump) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return toString(0);
    }

    private String toString(int orientation) {
        String[][] canvas = new String[5][5];
        writeOnStringMatrix(orientation, 0, 0, canvas);
        return stringMatrixToString(canvas);
    }

    public void writeOnStringMatrix(int orientation, int iOffset, int jOffset, String[][] matrix) {
        boolean inv = orientation >= 4;
        int start = ((orientation) % 4) * 4;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i > 0 && j > 0 && i < 4 && j < 4) {
                    matrix[iOffset + i][jOffset + j] = "██";
                    continue;
                }
                int idx = start + 16;
                if (i == 0) {
                    idx += inv ? 4 - j : j;
                } else if (i == 4) {
                    idx += inv ? 8 + j : 12 - j;
                } else if (j == 4) {
                    idx += inv ? 16 - i : 4 + i;
                } else {// j == 0
                    idx += inv ? 4 + i : 16 - i;
                }
                if (sides[idx % 16]) {
                    matrix[iOffset + i][jOffset + j] = "██";
                }
            }
        }
    }
}
