package GraphX;

/**
 * 959. Regions Cut By Slashes
 */
public class RegionsCutBySlashes {
    private int[] parent;
    private int[] treeSize;
    private int componentCount;

    /**
     * 本题主要难在查并集里的元素到底表示啥？
     * 如果按照题意，/或者\拆分一个方格，将一个方格拆分为2份，元素是一个方格的1/2，那么方格间合并时，还需要判断对应方格是「斜杠」还是「反斜杠」
     * 但是如果将一个方格拆的更小，更统一点，拆为4份，那么就无需判断相邻的方格的内容了。
     */
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;

        // 初始化查并集的parent-link array，tree size array
        componentCount = n * n * 4;
        parent = new int[componentCount];
        treeSize = new int[componentCount];
        for (int i = 0; i < componentCount; i++){
            parent[i] = i;
            treeSize[i] = 1;
        }

        // 一共有n*n个方格，对于每个方格里，被拆分成4块，按照上、右、下、左，分别编码为0、1、2、3，
        // 比如对于第i行的第j列的方格，基础索引就是：(i * n + j ) * 4, 然后分别加上0、1、2、3
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                char c = grid[i].charAt(j);
                int curr = (i * n + j ) * 4;
                if (c == ' '){
                    union(curr, curr + 1);
                    union(curr, curr + 2);
                    union(curr, curr + 3);
                } else if (c == '/'){
                    union(curr, curr + 3);
                    union(curr + 1, curr + 2);
                } else {
                    union(curr, curr + 1);
                    union(curr + 2, curr + 3);
                }
                // 如果不是第一列，则向前合并
                if (j > 0){
                    int left = (i * n + j - 1) * 4;
                    union(curr + 3, left + 1);
                }
                // 想上合并
                if (i > 0){
                    int up = ((i - 1) * n + j) * 4;
                    union(curr, up + 2);
                }
            }
        }

        return componentCount;
    }

    private void union(int v, int w){
        int vRoot = find(v);
        int wRoot = find(w);
        if (vRoot != wRoot){
            if (treeSize[vRoot] < treeSize[wRoot]){
                parent[vRoot] = wRoot;
                treeSize[wRoot] += treeSize[vRoot];
            } else {
                parent[wRoot] = vRoot;
                treeSize[vRoot] += treeSize[wRoot];
            }
            componentCount--;
        }
    }

    private int find(int v){
        while (v != parent[v]){
            v = parent[v];
        }
        return v;
    }

    public static void main(String[] args) {
        RegionsCutBySlashes inst = new RegionsCutBySlashes();
        String[] grid = new String[]{" /", "/ "};
        int ret = inst.regionsBySlashes(grid);
        System.out.println(ret);
        if (ret != 2){
            throw new AssertionError();
        }

        String[] grid2 = new String[]{"/\\", "\\/"};
        int ret2 = inst.regionsBySlashes(grid2);
        System.out.println(ret2);
        if (ret2 != 5){
            throw new AssertionError();
        }
    }
}
