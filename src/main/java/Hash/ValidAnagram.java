package Hash;

import java.util.HashMap;

/**
 * 242
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.equals(t)) {
            return false;
        }
        int len1 = s.length();
        int len2 = t.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len1; i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < len2; i++){
            if (!map.containsKey(t.charAt(i))){
                return false;
            }
            int cnt = map.getOrDefault(t.charAt(i), 0) - 1;
            if (cnt == 0){
                map.remove(t.charAt(i));
            } else {
                map.put(t.charAt(i), cnt);
            }
        }

        return map.size() == 0;
    }

}
