public class AList {

    private int[] list;
    private int contentSize;

    public AList() {
        list = new int[100];
        contentSize = 0;
    }

    public void addLast(int x) {
        if (contentSize == list.length) {

            int[] newL = new int[list.length + 1];
            for (int i = 0; i < contentSize; i++) {
                newL[i] = list[i];
            }
            /*
             * don't forget to copy the vulues to the
             * new allocated array
             */
            list = newL;
            list[contentSize] = x;
            contentSize += 1;
            return;
        }
        list[contentSize] = x;
        contentSize += 1;
    }

    public int getLast() {
        if (contentSize == 0) {
            return 0;
        }
        return list[contentSize - 1];
    }

    public int get(int i) {
        if (i > contentSize || i < 0) {
            return 0;
            // can't return null?
        }
        return list[i];
    }

    public int size() {
        return contentSize;
    }

    public int removeLast() {
        if (contentSize == 0) {
            return 0;
        }
        int removeN = list[contentSize - 1];
        contentSize -= 1;
        return removeN;
    }

    public void printList() {
        for (int i = 0; i < contentSize; i++) {
            System.out.println(list[i]);
        }
    }

    public void capacity() {
        System.out.printf("the capacity of the AList is %d\n", list.length);
        System.out.printf("the content size of the AList is %d\n", contentSize);
    }
}
