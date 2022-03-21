
public class ArrayDeque<T> {
    private T[] items;
    private int contentSize;
    private int head;
    private int tail;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        contentSize = 0;
        head = 0;
        tail = 1;
        /*
         * head and tail point at the unpopulated positions
         * head increase with counter-clockwise, tail increase with clockwise
         * invariant 1: head and tail must never collide.
         * invariant 2: contentSize<(items.length-2)
         * 
         */
    }

    public ArrayDeque(ArrayDeque other) {
        if (other == null) {
            return;
        }
        items = (T[]) new Object[other.items.length];
        // shuld use other.items.length here,
        // not other.contentSize, course the items are placed unreguarlarly
        System.arraycopy(other.items, 0, items, 0, other.items.length);
        contentSize = other.contentSize;
        head = other.head;
        tail = other.tail;
    }

    private int modCapacity(int index) {
        if (index < 0) {
            return items.length + (index % items.length);
        }
        /*
         * deal with negative index
         * a negative index means it wraps back the other end of the array
         */
        return index % items.length;
    }

    private void enlargeCapacity(int capacity) {
        T[] newA = (T[]) new Object[capacity];
        if (modCapacity(head) < modCapacity(tail)) {
            System.arraycopy(items, 0, newA, 0, items.length);
            /*
             * items.length, not contentSize, the items are not placed regularly
             * and their positions do't align to the right or left side of the array
             */
            items = newA;
            return;
        }
        System.arraycopy(items, 0, newA, 0, modCapacity(tail));
        int newHead = (capacity - 1) - (contentSize - modCapacity(tail));
        System.arraycopy(items, modCapacity(head + 1), newA, newHead + 1, contentSize - tail);
        head = newHead;
        items = newA;
    }

    public int size() {
        return contentSize;
    }

    public void addFirst(T item) {
        if (contentSize < (items.length - 2)) {
            items[modCapacity(head)] = item;
            head -= 1;
            contentSize += 1;
            return;
        } else {
            enlargeCapacity(items.length * 2);
            addFirst(item);
        }
    }

    public void addLast(T item) {
        if (contentSize < (items.length - 2)) {
            items[modCapacity(tail)] = item;
            tail += 1;
            contentSize += 1;
            return;

        } else {
            enlargeCapacity(items.length * 2);
            addLast(item);
        }
    }

    public boolean isEmpty() {
        if (contentSize == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void printDeque() {
        if (modCapacity(head) < modCapacity(tail)) {
            for (int i = modCapacity(head) + 1; i < modCapacity(tail); i++) {
                System.out.print(items[i]);
                System.out.print(" ");
            }
        } else {
            for (int i = modCapacity(head) + 1; i < items.length; i++) {
                System.out.print(items[i]);
                System.out.print(" ");
            }
            for (int i = 0; i < modCapacity(tail); i++) {
                System.out.print(items[i]);
                System.out.print(" ");
            }
        }
        System.out.println("");
    }

    public T removeFirst() {
        if (contentSize != 0) {
            T dumb = items[modCapacity(head + 1)];
            // head%length+1 is not equal to (head+1)%length
            head += 1;
            contentSize -= 1;
            items[modCapacity(head)] = null;
            if (calcRatio() < 0.25 && items.length != 8) {
                shrinkCapacity(items.length / 2);
            }
            return dumb;
        } else {
            return null;
        }
    }

    public T removeLast() {
        if (contentSize != 0) {
            T dumb = items[modCapacity(tail - 1)];
            tail -= 1;
            contentSize -= 1;
            items[modCapacity(tail)] = null;
            if (calcRatio() < 0.25 && items.length != 8) {
                shrinkCapacity(items.length / 2);
            }
            return dumb;
        } else {
            return null;
        }
    }

    public T get(int index) {
        if (index > contentSize) {
            return null;
        } else if (index < 0) {
            return null;
        } else {

            return items[modCapacity(head + 1 + index)];
        }
    }

    private double calcRatio() {
        double ratio = (double) contentSize / (double) items.length;
        // double ratio = contentSize / items.length ; doesn't work
        // if (ratio < 0.25) {
        // System.out.printf("%d / %d,the ratio is %f\n", contentSize, items.length,
        // ratio);
        // }
        return ratio;
    }

    private void shrinkCapacity(int capacity) {
        T[] newA = (T[]) new Object[capacity];
        if (modCapacity(head) < modCapacity(tail)) {
            System.arraycopy(items, modCapacity(head + 1), newA, 1, contentSize);
            items = newA;
            head = 0;
            tail = contentSize + 1;
            return;
        }
        System.arraycopy(items, 0, newA, 0, modCapacity(tail));
        int newHead = (capacity - 1) - (contentSize - modCapacity(tail));
        System.arraycopy(items, modCapacity(head + 1), newA, newHead + 1,
                contentSize - tail);
        head = newHead;
        items = newA;
    }

    private void getCapacity() {
        System.out.printf("the cap is %d\n", items.length);
    }
}
