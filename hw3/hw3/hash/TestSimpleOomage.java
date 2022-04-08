package hw3.hash;

import org.junit.Test;

import edu.princeton.cs.algs4.StdRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        SimpleOomage ooA = new SimpleOomage(5, 5, 5);
        SimpleOomage ooA2 = new SimpleOomage(15, 0, 0);
        HashSet<SimpleOomage> hashSet2 = new HashSet<>();
        hashSet2.add(ooA);
        System.out.printf("hashcode ooa:%d, ooa2:%d\n", ooA.hashCode(), ooA2.hashCode());
        System.out.println(hashSet2.contains(ooA2));
        /*
         * The contains method uses the object's equals and hashCode method in tandem to
         * determine whether the node is contained in the set.
         * So even the two objects have the same hashcode, if the two are't equal, the
         * contains method would still return false.
         * Don't use the contains method to check if two objects have the same hashCode.
         */
        assertFalse(hashSet2.contains(ooA2));

        for (int times = 0; times < 1000; times++) {

            int red = StdRandom.uniform(51) * 5;
            int green = StdRandom.uniform(51) * 5;
            int blue = StdRandom.uniform(51) * 5;
            SimpleOomage rd = new SimpleOomage(red, green, blue);
            HashSet<SimpleOomage> hashSet = new HashSet<>();
            hashSet.add(rd);

            for (int i = 0; i <= 255; i += 5) {
                for (int j = 0; j <= 255; j += 5) {
                    for (int k = 0; k <= 255; k += 5) {
                        SimpleOomage against = new SimpleOomage(i, j, k);
                        if (against.equals(rd) == false) {
                            // System.out.printf("red:%d, green:%d, blue:%d AGAINST red:%d, green:%d,
                            // blue:%d\n", red,
                            // green, blue, i, j, k);
                            // assertFalse(hashSet.contains(against));
                            assertNotEquals(rd + " against " + against, rd.hashCode(), against.hashCode());
                        }
                    }
                }
            }

        }

    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /*
     * TODO: Uncomment this test after you finish haveNiceHashCode Spread in
     * OomageTestUtility
     */

    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
