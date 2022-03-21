public class IntList {
    public int number;
    public IntList rest;

    public IntList(int num, IntList list) {
        number = num;
        rest = list;
    }

    public static int size(IntList next) {
        // int runningSum = 0;
        // IntList next = rest;
        // while (next != null) {

        // }
        // return runningSum;
        if (next == null) {
            return 0;
        }
        return 1 + size(next.rest);
    }

    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + rest.size();
    }

    public int iterativeSize() {
        int runningSum = 1;
        // IntList next=this;
        IntList next = rest;
        while (next != null) {
            runningSum += 1;
            next = next.rest;
        }
        return runningSum;
    }

    public int get(int i) {
        IntList current = this;
        int index = 0;
        while (index < i) {
            index += 1;
            current = this.rest;
        }
        return current.number;
    }

    private static IntList copyList(IntList L) {
        if (L.rest == null) {
            return new IntList(L.number, null);
        }
        return new IntList(L.number, copyList(L.rest));
    }

    private static void noRetIncrList(IntList L, int x) {
        if (L.rest == null) {
            L.number += x;
            return;
        }
        L.number += x;
        noRetIncrList(L.rest, x);
        return;

    }

    public static IntList incrList(IntList L, int x) {
        IntList copyL = copyList(L);
        noRetIncrList(copyL, x);
        return copyL;
    }

    public static IntList dincList(IntList L, int x) {
        noRetIncrList(L, x);
        return L;
    }

    public void addFirst(int x) {
        if (this.rest == null) {
            this.rest = new IntList(this.number, null);
            this.number = x;
            return;
        }
        this.rest.addFirst(this.number);
        this.number = x;
        return;
    }

    public static void main(String[] args) {
        IntList list = new IntList(1, null);
        list = new IntList(2, list);
        list = new IntList(3, list);

        System.out.printf("size is :%d\n", IntList.size(list));
        System.out.printf("size is :%d\n", list.size());

        IntList copyL = incrList(list, 100);

        System.out.printf("the %d th element of origin list is %d\n", 2, list.get(2));
        System.out.printf("the %d th element of copyL is %d\n", 2, copyL.get(2));

        IntList inPlaceIncL = dincList(list, 200);
        System.out.printf("the %d th element of origin list is %d\n", 2, list.get(2));

        list.addFirst(111111);
        System.out.printf("the %d th element of origin list is %d\n", 0, list.get(0));

    }
}
