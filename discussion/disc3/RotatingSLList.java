public class RotatingSLList<T> extends SLList<T> {

    public static void rotateRight(SLList sll) {
        sll.addFirst(sll.removeLast());
    }

}
