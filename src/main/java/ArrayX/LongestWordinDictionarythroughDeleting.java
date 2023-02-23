package ArrayX;

import Util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 524. Longest Word in Dictionary through Deleting
 */
public class LongestWordinDictionarythroughDeleting {

    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((o1, o2) -> {
            int l1 = o1.length();
            int l2 = o2.length();
            if (l1 != l2) {
                return l2 - l1;
            } else {
                return o1.compareTo(o2);
            }
        });

        Utils.show(dictionary);

        for (String d: dictionary){
            int i = 0;
            for (int j = 0; i < d.length() && j < s.length(); ){
                if (d.charAt(i) == s.charAt(j)){
                    i++;
                    j++;
                } else {
                    j++;
                }
            }
            if (i == d.length()){
                return d;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        LongestWordinDictionarythroughDeleting inst = new LongestWordinDictionarythroughDeleting();
        List<String> dictionary  = new ArrayList<>();
        dictionary.add("ale");
        dictionary.add("apple");
        dictionary.add("monkey");
        dictionary.add("plea");
        System.out.println(inst.findLongestWord("apple", dictionary));

        List<String> dictionary1  = new ArrayList<>();
        dictionary1.add("a");
        dictionary1.add("b");
        dictionary1.add("c");
        System.out.println(inst.findLongestWord("abpcplea", dictionary1));
    }
}
