import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class PrimitiveArray {
    public static int[] insert(int[] arr, int item, int position) {
        return new int[2];
    }

    public static void reverseIteratively(int[] arr) {
        int middle = (int) Math.ceil((double) arr.length / 2.0);
        for (int i = 0; i < middle; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    public static int[] replicate(int[] arr) {
        int runningSum = 0;
        for (int i : arr) {
            runningSum += i;
        }
        int running = 0;
        int[] newArr = new int[runningSum];
        for (int i = 0; i < arr.length; i++) {
            for (int j = running; j < running + arr[i]; j++) {
                // the limit of j also recalculate according to the new beginning index
                newArr[j] = arr[i];
            }
            running += arr[i];
            // have to recalculate the beginning index of the next turn
            // two different indices translation
        }
        return newArr;
    }

    @Test
    public void testReverseIteratively() {
        int[] arr = new int[] { 1, 2 };
        reverseIteratively(arr);
        int[] expecteds = new int[] { 2, 1 };
        assertArrayEquals(expecteds, arr);
    }

    @Test
    public void testReplicate() {
        int[] arr = new int[] { 4, 2, 3 };
        int[] expecteds = new int[] { 4, 4, 4, 4, 2, 2, 3, 3, 3 };
        int[] act = replicate(arr);
        assertArrayEquals(expecteds, act);
    }
}
