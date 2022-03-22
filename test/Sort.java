public class Sort {
    public static void sort(String[] x) {
        if (x.length == 1) {
            return;
        }
        int smallIndex = 0;
        for (int i = 1; i < x.length; i++) {
            if (x[smallIndex].compareToIgnoreCase(x[i]) > 0) {
                smallIndex = i;
            }
        }
        String temp = x[0];
        x[0] = x[smallIndex];
        x[smallIndex] = temp;
        sort((String[]) x[1]);
        org.junit.Assert.assertEquals(1, 2, 3);
    }
}
