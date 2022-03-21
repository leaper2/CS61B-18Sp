public class Test {
    public static void main(String[] args) {

        ArrayDeque adl = new ArrayDeque<>();
        adl.addLast(0);
        adl.removeFirst();
        adl.addLast(2);
        adl.removeFirst();
        adl.addFirst(4);
        adl.removeLast();
        adl.addLast(6);
        adl.addLast(7);
        adl.get(1);
        adl.get(0);
        adl.addLast(10);
        adl.addLast(11);
        adl.removeLast();
        adl.removeLast();
        adl.addLast(14);
        adl.get(1);
        adl.removeLast();
        adl.addLast(17);
        adl.addLast(18);
        adl.addLast(19);
        adl.addLast(20);
        adl.addLast(21);

    }
}
