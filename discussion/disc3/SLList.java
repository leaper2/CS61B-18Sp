import javax.security.auth.login.CredentialException;

public class SLList<T> {
    private class IntNode {
        public T item;
        public IntNode next;

        public IntNode(T item, IntNode next) {
            this.item = item;
            this.next = next;
        }

    }

    private IntNode first;

    public SLList() {
        first = null;
    }

    public SLList(T x) {
        first = new IntNode(x, null);
    }

    public void addFirst(T x) {
        first = new IntNode(x, first);
    }

    public T getFirst() {
        return first.item;
    }

    public void addLast(T x) {
        if (this.first == null) {
            first = new IntNode(x, null);
            return;
        }
        IntNode currentNode = first;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = new IntNode(x, null);
    }

    private int size(IntNode current) {
        if (current.next == null) {
            return 1;
        }
        return 1 + size(current.next);
    }

    public int size() {
        return size(first);
    }

    public void insert(T item, int position) {
        IntNode current = first;
        IntNode previous = null;
        if (position == 0) {
            addFirst(item);
            return;
        }
        while (current.next != null && position > 0) {
            position--;
            previous = current;
            current = current.next;
        }
        if (position > 0) {
            current.next = new IntNode(item, null);
            return;
        }
        IntNode insertN = new IntNode(item, current);
        previous.next = insertN;
    }

    public void reverseIteratively() {
        IntNode remainOriginNodes = first, current = null;
        /*
         * Current must be null initially, for if current==first, then the first node
         * would be
         * skipped,its next field would not be correctly modified.
         * And even worse, because frontOfReversed is initialed with null, which is
         * assigned to the
         * first current node's next field, which results in the loss of the skipped
         * node.
         */
        IntNode frontOfReversed = null;
        while (remainOriginNodes != null) {
            current = remainOriginNodes;
            remainOriginNodes = remainOriginNodes.next;
            current.next = frontOfReversed;
            frontOfReversed = current;
        }
        first = frontOfReversed;
    }

    public T removeLast() {
        IntNode current = first, previous = null;
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        previous.next = null;
        return current.item;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == ((SLList) obj)) {
            return true;
        }
        if (this.size() != ((SLList) obj).size()) {
            return false;
        }
        IntNode currentThis = first, currentAgainst = ((SLList) obj).first;
        boolean result = true;
        while (currentThis.next != null) {
            result = currentThis.item == currentAgainst.item;
            if (!result) {
                break;
            }
            currentThis = currentThis.next;
            currentAgainst = currentAgainst.next;
        }
        return result;
    }

    public void print() {
        IntNode current = first;
        while (current != null) {
            System.out.print(current.item + " ");
        }
        System.out.println("");
    }

}