import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class HellOtus {
    public static void main(String[] args) {
        String[] words = new String[] {"Karl", "Peter", "Friedrich", "Karl"};
        Multiset<String> counts = HashMultiset.create();
        for (String word : words) {
            counts.add(word);
        }
        System.out.println(counts.count("Karl"));
    }
}
