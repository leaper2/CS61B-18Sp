public class Test {
    public static void main(String[] args) {
        // System.out.println(-9 % 8);
        int[] expected = new int[] { 1, 2, 3 };
        int[] actual = new int[] { 1, 2, 3 };
        org.junit.Assert.assertArrayEquals(expected, actual);
    }
}
