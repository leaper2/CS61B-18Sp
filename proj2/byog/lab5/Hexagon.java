package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Hexagon {
    private static final int WIDTH = 7;
    private static final int HEIGHT = 7;

    // public static void main(String[] args) {
    // TERenderer ter = new TERenderer();
    // ter.initialize(WIDTH, HEIGHT);
    // Hexagon hg = new Hexagon();
    // TETile[][] world = hg.initializeTiles(3);
    // TETile[][] world2 = hg.reverseIteratively(world);
    // ter.renderFrame(world);
    // ter.renderFrame(world2);
    // }

    public void addHexagon(int s) {

    }

    private int column(int length) {
        return calcArithmSeqNth(length, length, 2);
    }

    private void fillEmpty(TETile[][] world) {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < column(world.length); j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    private TETile[][] initializeTiles(int length) {
        TETile[][] matrix = new TETile[length][column(length)];
        fillEmpty(matrix);
        fillShape(matrix, length);
        return matrix;
    }

    private static int calcArithmSeqNth(int a1, int nth, int diff) {
        return a1 + (nth - 1) * diff;
    }

    private void fillShape(TETile[][] matrix, int length) {
        for (int i = 0; i < length; i++) {
            // for (int j = 0; j < calcArithmSeqNth(0, length - i, 1); j++) {
            // matrix[i][j] = 1;
            // }
            for (int j = calcArithmSeqNth(0, length - i, 1); j < (calcArithmSeqNth(0, length - i, 1)
                    + calcArithmSeqNth(length, i + 1, 2)); j++) {
                matrix[i][j] = Tileset.PLAYER;
            }
        }
    }

    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j : matrix[i]) {
                System.out.print(j + " ");
            }
            System.err.println("");
        }
    }

    private TETile[][] reverseIteratively(TETile[][] arr) {
        TETile[][] arrCopy = arr.clone();
        int middle = (int) Math.ceil((double) arrCopy.length / 2.0);
        for (int i = 0; i < middle; i++) {
            TETile[] temp = arrCopy[i];
            arrCopy[i] = arrCopy[arrCopy.length - i - 1];
            arrCopy[arrCopy.length - i - 1] = temp;
        }
        return arrCopy;
    }
}
