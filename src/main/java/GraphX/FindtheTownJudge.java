package GraphX;

public class FindtheTownJudge {

    /**
     * 图：1。小镇法官的出度是0，2。入度一定是N-1
     * */

    public int findJudge(int n, int[][] trust) {
        int[] in = new int[n];
        int[] out = new int[n];

        // ！！注意下标是从1开始
        for (int[] t: trust){
            out[t[0] - 1]++;
            in[t[1] - 1]++;
        }

        for (int i = 0; i < n; i++){
            if (in[i] == n-1 && out[i] == 0){
                return i+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindtheTownJudge inst = new FindtheTownJudge();
        int ret = inst.findJudge(3, new int[][]{{1,3},{2,3},{3,1}});
        System.out.println(ret);
        int ret2 = inst.findJudge(3, new int[][]{{1,3},{2,3}});
        System.out.println(ret2);
    }
}
