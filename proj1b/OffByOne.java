public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        if (x == 0 || y == 0) {
            return true;
        }
        return Math.abs(x - y) == 1;
    }

}
