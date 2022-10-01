package TwoPointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 187. Repeated DNA Sequences
 */
public class RepeatedDNASequences {

    static final int L = 10;

    /**
     * 时间、空间复杂度：O(NL)
     */
    public List<String> findRepeatedDnaSequences2(String s) {
        List<String> ret = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        int len = s.length();
        for (int i = 0; i <= len - L; i++) {  // 注意substring是左闭右开，所有右边最长是len，那么左边最长就是len-10
            String sub = s.substring(i, i + L);
            map.put(sub, map.getOrDefault(sub, 0) + 1);
            if (map.get(sub) == 2) {  // 为避免加重复的子数组，只有重复第2次就加入
                ret.add(sub);
            }
        }

        return ret;
    }

    /**
     * 优化复杂度为：O(N)
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ret = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        int len = s.length();
        // 因为有4个字符，可以将每个字符映射为2个bit，一共10个字符长的单词，就可以压缩到20个bit，一个int就可以存储
        HashMap<Character, Integer> bin = new HashMap<>();
        bin.put('A', 0);
        bin.put('C', 1);
        bin.put('G', 2);
        bin.put('T', 3);
        if (len < L){
            return ret;
        }

        int curr = 0;
        for (int i = 0; i < L - 1; i++){ // 先算前9个字符
            curr <<= 2;
            curr |= bin.get(s.charAt(i));
        }

        // 哈希key不用10个字符，改为使用int来表示后，可以O(1)的时间算出是否重复
        for (int i = L - 1; i < len; i++){
            curr <<= 2;
            curr |= bin.get(s.charAt(i));
            curr &= ((1 << (2 * L)) - 1);  // 只保留低20位，高位置0
            map.put(curr, map.getOrDefault(curr, 0) + 1);
            if (map.get(curr) == 2){
                ret.add(s.substring(i - L + 1, i + 1)); // 这里还是需要O(10), 注意是右开，所以都要+1
            }
        }

        return ret;
    }

    static private void show(List<String> strings){
        for(String s: strings){
            System.out.print(s + ",");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        RepeatedDNASequences inst = new RepeatedDNASequences();
        show(inst.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}
