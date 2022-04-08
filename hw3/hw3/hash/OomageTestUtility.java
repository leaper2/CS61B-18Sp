package hw3.hash;

import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        HashMap<Integer, Integer> hm = new HashMap<>(M);
        int bucketNum;
        int temp;
        // don't forget to initialize the hashmap
        for (int i = 0; i < M; i++) {
            hm.put(i, 0);
        }
        for (Oomage om : oomages) {
            int hashcode = om.hashCode();
            bucketNum = (om.hashCode() & 0x7FFFFFFF) % M;
            temp = hm.get(bucketNum);
            // Note: here donot use temp++ , that would use the 0 as the input value
            // donot use temp++ within a expression
            hm.put(bucketNum, ++temp);
        }

        for (int i = 0; i < M; i++) {
            temp = hm.get(i);
            if (temp < oomages.size() / 50 || temp > oomages.size() / 2.5) {
                return false;
            }
        }
        return true;
    }
}
