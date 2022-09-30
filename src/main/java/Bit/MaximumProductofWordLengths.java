package Bit;

/**
 * 318. 最大单词长度乘积
 */
public class MaximumProductofWordLengths {

    public int maxProduct(String[] words) {
        int len = words.length;

        // 位掩码：每个单词用int存储它的26个字母的标记位
        int[] masks = new int[len];
        for (int i = 0; i < len; i++){
            for (int j = 0; j < words[i].length(); j++){
                masks[i] |= (1 << (words[i].charAt(j) - 'a'));
            }
        }

        int ret = 0;
        for (int i = 1; i < len; i++){
            for (int j = 0; j < i; j++){
                if ((masks[i] & masks[j]) == 0){
                    ret = Math.max(ret, words[i].length() * words[j].length());
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        MaximumProductofWordLengths inst = new MaximumProductofWordLengths();
        System.out.println(inst.maxProduct(new String[]{"abcw","baz","foo","bar","xtfn","abcdef"}));
        System.out.println(inst.maxProduct(new String[]{"a","ab","abc","d","cd","bcd","abcd"}));
        System.out.println(inst.maxProduct(new String[]{"a","aa","aaa","aaaa"}));
    }
}
