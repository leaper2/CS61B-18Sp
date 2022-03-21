public class TestArrayDeque {
    public static void main(String[] args) {
        ArrayDeque<Integer> al = new ArrayDeque<>();
        al.addFirst(1);
        al.addLast(2);
        al.printDeque();
        System.out.printf("the size of the al is %d\n", al.size());
        for (int i = 0; i < 12; i++) {
            al.addFirst(i);
        }
        al.printDeque();
        System.out.printf("the size of the al is %d\n", al.size());
        ArrayDeque<Integer> cpal = new ArrayDeque<>(al);
        cpal.printDeque();
        cpal.removeFirst();
        cpal.printDeque();
        for (int i = 0; i < 100; i++) {
            cpal.removeLast();
        }
        cpal.printDeque();
        System.out.printf("the size of the al is %d\n", cpal.size());
        for (int i = 0; i < 14; i++) {
            cpal.addLast(i);
        }
        cpal.printDeque();

        System.out.printf("the %d th of the cpal is %d\n", 11, cpal.get(11));
        for (int i = 0; i < 11; i++) {
            cpal.removeLast();
        }
        cpal.printDeque();
        cpal.removeLast();
        // cpal.getCapacity();
        cpal.printDeque();
    }

}
