package byog.lab5;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntHexagon {
    // public static void main(String[] args) {
    // IntHexagon hg = new IntHexagon();
    // int[][] matrix = hg.calcMatrix(3);
    // // hg.printMatrix(matrix);
    // int[][] matrixCopy = hg.reverseIteratively(matrix);
    // // hg.printMatrix(matrixCopy);
    // int[][] combinedRec = hg.combineIntoRec(matrix, matrixCopy);
    // hg.printMatrix(combinedRec);
    // hg.printShape(combinedRec);
    // }

    public void addHexagon(int[][] shapeWorld, int unitRecLength, int[][] hexagon) {
        int length = shapeWorld.length;
        int midRow = (int) Math.floor((double) length / 2.0 * unitRecLength);
        for (int i = 0; i < unitRecLength * 2; i++) {
            for (int j = midRow; j < midRow + column(unitRecLength); j++) {

            }
        }

    }

    private int[][] calcWorld(int length) {
        int areaUnitRec = column(length) * column(length);
        int areaShapeWorld = areaUnitRec * 19;
        int worldLength = (int) Math.sqrt(areaShapeWorld) + 1;
        int[][] shapeWorld = new int[worldLength][worldLength];
        return shapeWorld;
    }

    private int[][] combineIntoRec(int[][] matrix1, int[][] matrix2) {
        int[][] combinedRec = new int[matrix1.length * 2][column(matrix1.length)];
        for (int i = 0; i < combinedRec.length; i++) {
            if (i < matrix1.length) {
                for (int j = 0; j < column(matrix1.length); j++) {
                    combinedRec[i][j] = matrix1[i][j];
                }
            } else {
                for (int j = 0; j < column(matrix1.length); j++) {
                    combinedRec[i][j] = matrix2[i - matrix1.length][j];
                }
            }
        }
        return combinedRec;
    }

    private int column(int length) {
        return calcArithmSeqNth(length, length, 2);
    }

    private int[][] calcMatrix(int length) {
        int[][] matrix = new int[length][column(length)];
        fillEmpty(matrix, length);
        return matrix;
    }

    private int calcArithmSeqNth(int a1, int nth, int diff) {
        return a1 + (nth - 1) * diff;
    }

    private void fillEmpty(int[][] matrix, int length) {
        for (int i = 0; i < length; i++) {
            // for (int j = 0; j < calcArithmSeqNth(0, length - i, 1); j++) {
            // matrix[i][j] = 1;
            // }
            for (int j = calcArithmSeqNth(0, length - i, 1); j < (calcArithmSeqNth(0, length - i, 1)
                    + calcArithmSeqNth(length, i + 1, 2)); j++) {
                matrix[i][j] = 1;
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

    public void printShape(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j : matrix[i]) {
                if (j == 1) {
                    System.out.print("a");
                } else {
                    System.out.print(" ");
                }
            }
            System.err.println("");
        }
    }

    private int[][] reverseIteratively(int[][] arr) {
        int[][] arrCopy = arr.clone();
        int middle = (int) Math.ceil((double) arrCopy.length / 2.0);
        for (int i = 0; i < middle; i++) {
            int[] temp = arrCopy[i];
            arrCopy[i] = arrCopy[arrCopy.length - i - 1];
            arrCopy[arrCopy.length - i - 1] = temp;
        }
        return arrCopy;
    }

    @Test
    public void testCalcArithmSeq() {
        int rowHasShape = calcArithmSeqNth(4, 4, 2);
        assertEquals(10, rowHasShape);
    }

    @Test
    public void testFillEmpty() {
        int length = 3;
        int[][] mat1 = new int[length][column(length)];
        int[][] mat2 = new int[][] { { 0, 0, 1, 1, 1, 0, 0 }, { 0, 1, 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1, 1, 1 } };

        fillEmpty(mat1, length);
        assertArrayEquals(mat2, mat1);
    }
}
