public class Test {
    public static void main(String[] args) {
        LinkedListDeque<Integer> dll = new LinkedListDeque<>();
        dll.addFirst(1);
        dll.addLast(2);
        dll.addFirst(3);
        dll.printDeque();

        LinkedListDeque<Integer> dll2 = new LinkedListDeque<>(dll);
        dll2.addFirst(4);
        System.out.printf("the size of dll2 is %d\n", dll2.size());
        dll2.printDeque();
        System.out.printf("the %d th of dll2 is %d\n", 3, dll2.getRecursive(3));
        System.out.printf("the %d th of dll2 is %d\n", 3, dll2.get(3));

        dll2.removeFirst();
        dll2.printDeque();
        dll2.removeLast();
        dll2.printDeque();
        dll2.removeLast();
        dll2.removeLast();
        dll2.printDeque();
        dll2.addLast(7);
        dll2.printDeque();
        dll.printDeque();

        LinkedListDeque nullL = null;
        LinkedListDeque cpnull = new LinkedListDeque<>(nullL);
        cpnull.printDeque();
    }
}
