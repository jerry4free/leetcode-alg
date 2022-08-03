package SortX;

import java.util.*;

/**
 * 49. Group Anagrams
 */
public class GroupAnagrams {
    /**
     * 排序后的字符串作为哈希表的key
     * 时间复杂度：O(n * klgk), n是字符串个数，k是字符串的最大长度, klgk是每个字符串的排序时间，hash读写是O(1)
     * 空间复杂度：O(n * k)，哈希表的key是nk大小，value也是nk大小
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String >> map = new HashMap<>();

        for (String str: strs){
            // string to char array
            char[] chars = str.toCharArray();
            // arrays排序
            Arrays.sort(chars);
            // char array to string
            String sortedStr = String.valueOf(chars);

            // hash put, get
            if (map.containsKey(sortedStr)){
                map.get(sortedStr).add(str);
            } else {
                List<String> value = new ArrayList<>();
                value.add(str);
                map.put(sortedStr, value);
            }
        }

        List<List<String>> ret = new ArrayList<>();
        // for loop hashmap
        for (Map.Entry<String, List<String>> entry: map.entrySet()){
            ret.add(entry.getValue());
        }
        return ret;
    }

    /**
     * 频次表
     * 字符串排序后，可能还是有重复字母的。所以哈希表的key有没有更快更节省空间的标识办法呢？
     * 可以对单词的每个字母进行分桶计数，节省存储，同时避免排序，只需遍历一次
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str: strs){
            // 统计每个字母的个数
            int[] counts = new int[26];
            for(char c: str.toCharArray()){
                counts[c - 'a']++;
            }

            // build key
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++){
                if (counts[i] > 0){
                    sb.append((char)('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();

            List<String> value = map.getOrDefault(key, new ArrayList<>());
            value.add(str);
            map.put(key, value);
        }

        return new ArrayList<>(map.values());
    }

    private static void show(List<List<String>> strs){
        for (List<String> str: strs){
            for (String s: str){
                System.out.print(s + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GroupAnagrams inst = new GroupAnagrams();
        show(inst.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        show(inst.groupAnagrams(new String[]{""}));
        show(inst.groupAnagrams(new String[]{}));
    }
}
