import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void findABug() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        ArrayList<String> operationList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            double random = StdRandom.uniform();
            if (random < 0.5) {
                sad.addLast(i);
                ads.addLast(i);
                operationList.add("addLast(" + i + ")\n");
            }
        }
        for (int i = 0; i < ads.size(); i++) {
            assertEquals(ads.get(i), sad.get(i));
        }

        for (int i = 0; i < 100; i++) {
            double random = StdRandom.uniform();
            if (random > 0.5) {
                sad.removeFirst();
                ads.removeFirst();
                operationList.add("removeFirst()\n");
            }

        }

        for (int i = 0; i < ads.size(); i++) {
            assertEquals(String.join("", operationList), ads.get(i), sad.get(i));
        }
    }
}
