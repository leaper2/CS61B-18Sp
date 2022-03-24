import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.princeton.cs.algs4;

public class Test {
    public static void main(String[] args) {

    }

    public static List getWord(String inputFileName) {
        In file = new In(inputFileName);
        String[] strArray = file.readAllLines();
        List<E> list = new ArrayList<>();
        list.addAll(strArray);
    }

    public static int countUniqueWords(List<String> list) {
        Set set = new HashSet<>(list);
        return set.size();
    }
    public static 
}
