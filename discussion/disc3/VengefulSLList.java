public class VengefulSLList<T> extends SLList<T> {
    SLList<T> deletedItems;

    public void printLostedItems() {
        deletedItems.print();
    }

    @Override
    public T removeLast() {
        T remove = super.removeLast();
        deletedItems.addLast(remove);
        return remove;
    }
}
