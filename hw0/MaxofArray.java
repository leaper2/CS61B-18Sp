public class MaxofArray {
    public static void main(String[] args) {
        int[] intArray = new int[] { 9, 2, 15, 2, 22, 10, 6 };
        int maxint = max(intArray);
        System.out.format("the max number is %d\n", maxint);
    }

    public static int max(int[] m) {

        int runningMax = 0;
        for (int i = 0; i < m.length; i++) {

            if (runningMax < m[i]) {
                runningMax = m[i];
            }
        }
        return runningMax;
    }
}