package TwoPointer;

/**
 * 28
 */
public class FindtheIndexoftheFirstOccurrenceinaString {
    public int strStr(String haystack, String needle) {
        int len1 = haystack.length();
        int len2 = needle.length();
        if (len2 > len1){
            return -1;
        }

        for (int p = 0; p < len1 - len2 + 1; p++){
            int i = p, j = 0;
            for (; j < len2 && haystack.charAt(i) == needle.charAt(j); i++,j++){
            }
            if (j == len2){
                return p;
            }
        }

        return -1;
    }
}
