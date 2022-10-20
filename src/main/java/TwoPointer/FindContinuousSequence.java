package TwoPointer;

import java.util.*;

/**
 * 57: 和为s的连续正数序列
 */
public class FindContinuousSequence{

    private int sum(int start, int end){
        int ret = 0;
        while (start <= end){
            ret += start;
            start++;
        }
        return ret;
    }

    private List<Integer> getSeq(int start, int end){
        List<Integer> ret = new ArrayList<>();
        while (start <= end){
            ret.add(start);
            start++;
        }
        return ret;
    }

    public int[][] findContinuousSequence(int t){
        
        int i = 1;
        int j = 2;
        List<List<Integer>> ret = new ArrayList<>();
        while ((i + j) <= t && (j - i) > 0) {
            int s = sum(i, j);
            if (s < t){
                j++;
            } else if (s > t){
                i++;
            } else {
                ret.add(getSeq(i, j));
                j++;
            }
        }
        
        int[][] ans = new int[ret.size()][];
        for (i = 0; i < ret.size(); i++){
            List<Integer> seq = ret.get(i);
            int[] a = new int[seq.size()];
            for (j = 0; j < seq.size(); j++){
                a[j] = seq.get(j);
            }
            ans[i] = a;
        }
        return ans;
    }

    public static void show(int[][] ans){

        for (int[] a: ans){
            for (int i: a){
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        FindContinuousSequence inst = new FindContinuousSequence();
        show(inst.findContinuousSequence(1));
        show(inst.findContinuousSequence(2));
        show(inst.findContinuousSequence(9));
        show(inst.findContinuousSequence(15));
        show(inst.findContinuousSequence(100));

    }
}
