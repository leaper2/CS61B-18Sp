public class Test {
    public static void main(String[] args) {

        ArrayDeque adl = new ArrayDeque<>();
        adl.addFirst(0);
        adl.addFirst(1);
        adl.removeLast();
        adl.printDeque();
        adl.addFirst(3);
        adl.addFirst(4);
        adl.addFirst(5);
        adl.removeLast();
        adl.printDeque();
        adl.addFirst(7);
        adl.addFirst(8);
        adl.addFirst(9);
        adl.addFirst(10);
        adl.printDeque();

    }
}
