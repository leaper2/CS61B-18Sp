package hw3.hash;

public class Test {
    public static int hashCode(int[] array) {
        int total = 0;
        for (int element : array) {
            /*
             * Describe a rotate left shifting of 8bits, provided each elements is the
             * same integer
             */
            total = total * 256;
            total = total + element;
        }
        return total;
    }

    public static void main(String[] args) {
        int[] array1 = { 80, 103, 142, 91, 160, 250, 257, 257, 257, 257, 257 };
        int[] array2 = { 130, 105, 209, 257, 257, 257, 257 };
        int hashCode1 = hashCode(array1);
        int hashCode2 = hashCode(array2);
        System.out.println("hashCode1: " + hashCode1);// 117901063
        System.out.println("hashCode2: " + hashCode2);// 117901063

    }
}
