
public class Test {
    public static void main(String[] args) {
        SLList sll = new SLList(1);
        sll.addFirst(2);
        sll.addFirst(3);
        sll.addFirst(4);
        sll.addLast(5);

        System.out.printf("the size is %d\n", sll.size());
        System.out.printf("the fist is %d\n", sll.getFirst());

        AList al = new AList();
        System.out.printf("the size  of al is %d\n", al.size());
        al.addLast(1);
        al.addLast(2);
        System.out.printf("the size  of al is %d\n", al.size());
        al.printList();
        al.removeLast();
        al.printList();
        System.out.printf("the last of  al is %d\n", al.getLast());
        al.removeLast();
        System.out.printf("the last of  al is %d\n", al.getLast());
        al.removeLast();
        al.removeLast();
        System.out.printf("the last of  al is %d\n", al.getLast());
        System.out.printf("the size  of al is %d\n", al.size());
        al.capacity();
    }
}
