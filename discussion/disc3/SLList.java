public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }

    }

    private IntNode first;

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void insert(int item, int position) {
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
}