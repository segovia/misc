package segovia.cube_3d;

import java.util.Arrays;

public class Cube3d {

    private final static Square[] SQUARES = new Square[]{
            new Square("xx xxx x  x   x "),
            new Square("xx x  x   x   x "),
            new Square(" x x x xxx xx x "),
            new Square("  x  x x  x   x "),
            new Square("x x  x x  x  x x"),
            new Square(" x xxx x  x xx x")
    };

    /**
     * Prints all solutions of a 3d puzzle
     */
    public static void main(String[] args) {
        int[] squareInPos = new int[6];
        for (int i = 0; i < squareInPos.length; i++) {
            squareInPos[i] = i;
        }
        int[] orientation = new int[6];

        System.out.println(solve(1, squareInPos, orientation));
        orientation[0] = 4;
        System.out.println(solve(1, squareInPos, orientation));
    }

    public static int solve(int depth, int[] squareInPos, int[] orientation) {
        if (depth == squareInPos.length) {
            printResult(squareInPos, orientation);
            return 1;
        }
        int sum = 0;
        for (int i = depth; i < squareInPos.length; i++) {
            swap(depth, i, squareInPos);
            for (int j = 0; j < 8; j++) {
                orientation[depth] = j;
                if (isOK(depth, squareInPos, orientation)) {
                    sum += solve(depth + 1, squareInPos, orientation);
                }
            }
            swap(depth, i, squareInPos);
        }
        return sum;
    }

    private static void printResult(int[] squareInPos, int[] orientation) {
        Canvas canvas = new Canvas(3, 4);
        canvas.write(SQUARES[squareInPos[0]], orientation[0],1,0);
        canvas.write(SQUARES[squareInPos[1]], orientation[1],1,1);
        canvas.write(SQUARES[squareInPos[3]], orientation[3],1,2);
        canvas.write(SQUARES[squareInPos[4]], orientation[4],1,3);
        canvas.write(SQUARES[squareInPos[2]], orientation[2],0,1);
        canvas.write(SQUARES[squareInPos[5]], orientation[5],2,1);
        System.out.print(canvas);
        System.out.println(Arrays.toString(squareInPos));
    }

    private static boolean matches(int[] squareInPos, int[] orientation, int idxA, int sideA, int idxB, int sideB) {
        return SQUARES[squareInPos[idxA]].matches(sideA, orientation[idxA], SQUARES[squareInPos[idxB]], sideB, orientation[idxB]);
    }

    /**
     * _ 2
     * 0 1 3 4
     * _ 5
     */
    private static boolean isOK(int depth, int[] squareInPos, int[] orientation) {
        switch (depth) {
            case 1:
                return matches(squareInPos, orientation, 0, 1, 1, 3);
            case 2:
                return matches(squareInPos, orientation, 1, 0, 2, 2) &&
                        matches(squareInPos, orientation, 0, 0, 2, 3);
            case 3:
                return matches(squareInPos, orientation, 1, 1, 3, 3) &&
                        matches(squareInPos, orientation, 2, 1, 3, 0);
            case 4:
                return matches(squareInPos, orientation, 3, 1, 4, 3) &&
                        matches(squareInPos, orientation, 2, 0, 4, 0) &&
                        matches(squareInPos, orientation, 0, 3, 4, 1);
            case 5:
                return matches(squareInPos, orientation, 4, 2, 5, 2) &&
                        matches(squareInPos, orientation, 3, 2, 5, 1) &&
                        matches(squareInPos, orientation, 1, 2, 5, 0) &&
                        matches(squareInPos, orientation, 0, 2, 5, 3);
        }
        return true;
    }

    public static void swap(int i, int j, int[] vals) {
        if (i == j) return;
        int aux = vals[i];
        vals[i] = vals[j];
        vals[j] = aux;
    }
}
