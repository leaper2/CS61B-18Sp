public class SLList {
    private class IntNode {
        public int iterm;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            iterm = i;
            next = n;
        }
    }

    private IntNode first;

    public SLList() {
        first = null;
    }

    public SLList(int x) {
        first = new IntNode(x, null);
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public int getFirst() {
        return first.iterm;
    }

    public void addLast(int x) {
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

    private static int size(IntNode current) {
        if (current.next == null) {
            return 1;
        }
        return 1 + size(current.next);
    }

    public int size() {
        return size(first);
    }

    public int MFsize() {
        if (first.next == null) {
            return 1;
        }
        first = first.next;
        return 1 + this.MFsize();
    }

}
