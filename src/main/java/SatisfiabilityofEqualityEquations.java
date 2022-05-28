import java.util.HashMap;

public class SatisfiabilityofEqualityEquations {

    /**
     * 并查集, 变量看作点，方程式看作2点的关系，相等是连通的，不相等是不连通的
     * 先根据相等关系构建并查集，然后根据不想等判断是否属于不同集合。如果不存在矛盾情况，则说明符合条件
     */
    private int[] parent;
    private int[] treeSize;

    public boolean equationsPossible(String[] equations) {
        parent = new int[26];
        treeSize = new int[26];
        // init
        for (int i = 0; i < 26; i++){
            treeSize[i] = 1;
            parent[i] = i;
        }

        // 构建并查集
        for (String e: equations){
            if (e.substring(1,3).equals("==")){
                int v = e.charAt(0) - 'a';  // 
                int w = e.charAt(3) - 'a';
                union(v, w);
            }
        }

        // check
        for (String e: equations){
            if (e.substring(1,3).equals("!=")){
                int v = e.charAt(0) - 'a';
                int w = e.charAt(3) - 'a';
                if (find(v) == find(w)){
                    return false;
                }
            }
        }
        return true;
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
        }
    }

    private int find(int v){
        while (v != parent[v]){
            v = parent[v];
        }
        return v;
    }

    public static void main(String[] args) {
        SatisfiabilityofEqualityEquations inst = new SatisfiabilityofEqualityEquations();
        boolean ret = inst.equationsPossible(new String[]{"a==b","b!=a"});
        System.out.println(ret);
//        System.out.println("a==b".substring(1,3));
    }
}
