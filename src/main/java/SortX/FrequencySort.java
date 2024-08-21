package SortX;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class FrequencySort {

    public String frequencySort(String s) {
        // 字母+数字，ascii码都在128以内
        int[][] freq = new int[128][2];
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i);
            freq[idx][0] = idx;  // char -> cnt
            freq[idx][1] += 1;
        }

//        Arrays.sort(freq, Comparator.comparing(x -> -x[1])); // 降序排序
        Arrays.sort(freq, (o1, o2) -> o2[1] - o1[1]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < freq.length; i++) {
            for (int j = 0; j < freq[i][1]; j++) {
                sb.append((char)(freq[i][0]));
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        FrequencySort inst = new FrequencySort();
        System.out.println(inst.frequencySort("tree"));
    }

}
