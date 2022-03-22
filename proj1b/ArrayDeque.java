
public class ArrayDeque<T> implements Deque<T> {
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
         * Adding elements at the head increase head counter-clockwise,
         * Adding elements at the end increase tail clockwise
         * Invariant 1: head and tail must never collide.
         * Invariant 2: contentSize<(items.length-2)
         * 
         */
    }

    private ArrayDeque(ArrayDeque other) {
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

    private int modCapacity(int key) {
        // if (index < 0) {
        // return items.length + (index % items.length);
        // }
        // -8 % 8 ==0 + items.length , it's wrong
        int index = key % items.length;
        if (index < 0) {
            return items.length + index;
        }
        return index;
        /*
         * Deal with negative index
         * A negative index means it wraps back the other end of the array
         * The mod operation is a hash-mapping function actually. The values of
         * the head and the tail are used as keys, to map into the index of the
         * array.
         * 
         */
    }

    private void enlargeCapacity(int capacity) {
        T[] newA = (T[]) new Object[capacity];
        if (modCapacity(head) < modCapacity(tail)) {
            System.arraycopy(items, 0, newA, 0, items.length);
            /*
             * items.length, not contentSize, the items are not placed regularly
             * and their positions do't align to the right or left side of the array
             */
            head = modCapacity(head);
            tail = modCapacity(tail);
            /*
             * normalize head and tail, since length of items has change
             * for example, at the original array of capacity 8, -8 stand for 0,
             * but, with the new capacity 16, it means 8, so we have to make it
             * boid down to the actully index value of the original after changing capacity
             */
            items = newA;
            return;
        }
        System.arraycopy(items, 0, newA, 0, modCapacity(tail));
        int newHead = (capacity - 1) - (contentSize - modCapacity(tail));
        System.arraycopy(items, modCapacity(head + 1), newA, newHead + 1, contentSize - modCapacity(tail));
        head = newHead;
        tail = modCapacity(tail);
        items = newA;
    }

    public int size() {
        return contentSize;
    }

    public void addFirst(T item) {
        if (contentSize < (items.length - 2)) {
            items[modCapacity(head)] = item;
            head--;
            contentSize++;
            return;
        } else {
            enlargeCapacity(items.length * 2);
            addFirst(item);
        }
    }

    public void addLast(T item) {
        if (contentSize < (items.length - 2)) {
            items[modCapacity(tail)] = item;
            tail++;
            contentSize++;
            return;

        } else {
            enlargeCapacity(items.length * 2);
            addLast(item);
        }
    }

    @Override
    public boolean isEmpty() {
        return contentSize == 0;

    }

    public void printDeque() {
        if (modCapacity(head) < modCapacity(tail)) {
            for (int i = modCapacity(head) + 1; i < modCapacity(tail); i++) {
                System.out.print(items[i] + " ");
                // System.out.print(" ");
            }
        } else {
            for (int i = modCapacity(head) + 1; i < items.length; i++) {
                System.out.print(items[i] + " ");
                // System.out.print(" ");
            }
            for (int i = 0; i < modCapacity(tail); i++) {
                System.out.print(items[i] + " ");
                // System.out.print(" ");
            }
        }
        System.out.println("");
    }

    public T removeFirst() {
        if (contentSize != 0) {
            T dumb = items[modCapacity(head + 1)];
            // head%length+1 is not equal to (head+1)%length
            head++;
            contentSize--;
            items[modCapacity(head)] = null;
            if (calcRatio() < 0.25 && items.length != 8) {
                shrinkCapacity(items.length / 2);
            }
            return dumb;
        }
        return null;
    }

    public T removeLast() {
        if (contentSize != 0) {
            T dumb = items[modCapacity(tail - 1)];
            tail--;
            contentSize--;
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
        tail = modCapacity(tail);
        items = newA;
    }

    private void getCapacity() {
        System.out.printf("the cap is %d\n", items.length);
    }
}
