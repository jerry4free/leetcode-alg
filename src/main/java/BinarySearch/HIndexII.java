package BinarySearch;

/**
 * 275. H 指数 II
 */
public class HIndexII {
    public int hIndex(int[] citations) {
        int l = 0;
        int len = citations.length;
        int r = citations.length - 1;

        // 题目对h指数的定义是：有h篇论文至少h次。
        // 通过列举case: 0,1,3,5,6,7,8, 可以知道h指数其实是论文数量!!!而不是引用次数，因为有可能不存在某篇论文一定等于h
        // 找到一条分割线，分割线右侧有h篇论文，且右侧第一个>=h，左侧<h
        //
        while (l <= r){
            int m = l + (r - l) / 2;
            //  有len-m篇论文至少citation[m]次，但是len-m次小于citation[m], 意味着还可以往左找更多的论文
            if (citations[m] >= len - m){
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        // 最终有
        return len - l;
    }

    public int max(int a, int b){
        return Math.max(a, b);
    }

    public static void main(String[] args) {

        HIndexII inst = new HIndexII();
        System.out.println(inst.hIndex(new int[]{0,1,3,5,6}));
        System.out.println(inst.hIndex(new int[]{0,1,3,5,6,7,8}));
        System.out.println(inst.hIndex(new int[]{0,2,100}));
    }
}
