
public class LinkedListDeque<T> {
    private class TNode {
        private T item;
        private TNode previous;
        private TNode next;

        public TNode(T x, TNode p, TNode n) {
            item = x;
            previous = p;
            next = n;
        }

        public T getRec(int index) {
            if (index == 0) {
                return item;
            }
            return next.getRec(index - 1);
            /*
             * return getRec(index - 1);
             * call the neighbour's method, not the current's method!!!
             * whom the method is apply on is essential, because the method would
             * access the data the object
             */
        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque() {
        // sentinel=new TNode(null, sentinel, sentinel);
        // the empty circular list need the sentinal's pointers point to itself
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        // what is a generic value for placehould
        size = 0;
    }

    private LinkedListDeque(LinkedListDeque other) {
        this();
        /*
         * since this method is a constructor, it has to call the primitive
         * constructor to initilize the fields, otherwise, it must do it again itself
         */
        if (other == null) {
            System.out.println("Error, the other is null");
            return;
        }
        /*
         * always check the object from outside of a method
         */
        size = other.size;
        TNode currentThis = sentinel;
        TNode currentOther = other.sentinel.next;
        /*
         * because the next node must be attached to the current node's next field,
         * so the pointer of the origin list has to go ahead of one node position.
         */
        do {
            currentThis.next = new TNode(currentOther.item, currentThis, null);
            currentThis = currentThis.next;
            currentOther = currentOther.next;
        } while (currentOther.next != other.sentinel.next);
        /*
         * currentThis.next = new TNode(currentOther.item, currentThis, sentinel);
         * compare against the the sentinel.next as the threshold value, not the
         * setinel itself, then the last node can be manipulate consistently with
         * other nodes, spare us from having to deal with the last node as a special
         * case
         * 
         * since we start with the first node, we have to use the do-while style,
         * to let it skip the prematurely precluding check
         */
        sentinel.previous = currentThis;
        // don't forget to make sentinel's previous point to the tail
        currentThis.next = sentinel;
    }

    public T getRecursive(int index) {
        if (index < 0) {
            // System.out.println("index must be not less than 0");
            return null;
        } else if (index > size) {
            // System.out.printf("index must be less than %d", size);
            return null;
        }
        return sentinel.next.getRec(index);
    }

    public void addFirst(T item) {
        size++;
        TNode insertN = new TNode(item, sentinel, sentinel.next);
        sentinel.next = insertN;
        insertN.next.previous = insertN;
    }

    public void addLast(T item) {
        size++;
        TNode insertN = new TNode(item, sentinel.previous, sentinel);
        insertN.previous.next = insertN;
        sentinel.previous = insertN;
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode current = sentinel.next;
        do {
            System.out.print(current.item + " ");
            // System.out.print(" ");
            current = current.next;
        } while (current.next != sentinel.next);
        // current!=sentinel
        System.out.print("\n");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        TNode removeN = sentinel.next;
        sentinel.next = removeN.next;
        removeN.next.previous = sentinel;
        removeN.next = null;
        removeN.previous = null;
        return removeN.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        TNode removeN = sentinel.previous;
        sentinel.previous = removeN.previous;
        removeN.previous.next = sentinel;
        removeN.next = null;
        removeN.previous = null;
        return removeN.item;
    }

    public T get(int index) {
        if (index < 0) {
            System.out.println("index must be not less than 0");
            return null;
        } else if (index > size) {
            return null;
        }
        TNode current = sentinel.next;
        while (index > 0) {
            index--;
            current = current.next;
        }
        return current.item;
    }
}
