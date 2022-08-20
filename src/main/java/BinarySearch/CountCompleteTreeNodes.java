package BinarySearch;


/**
 * 222. Count Complete Tree Nodes
 */
public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        TreeNode node = root;
        // 求出深度, root从0开始
        int h = -1;
        while (node != null){
            h++;
            node = node.left;
        }
        if (h == 0){
            return 1;
        }

        int l = 1 << h; // 2^(h) - 1 + 1
        // TODO：注意位运算和加号在一起时的优先级，是加号优先，所以需要括号先左移再加1
        int r = (1 << (h + 1)) - 1; // 完全满二叉树的节点数：2^(h+1) - 1
        while (l <= r){
            int m = l + (r - l) / 2;
            if (exists(root, h, m)){
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;
    }

    // 如果判断第k个节点是否存在呢？
    // 左、右代表0、1，左右能代表二进制进行编码，进行计数，真是伟大的思想
    // 0:        1
    // 1:   10     11
    // 2: 100 101 110 111
    private boolean exists(TreeNode root, int h, int k){
        TreeNode node = root;
        h--; // h就是二进制的位数，除去最高位的1
        int bit = 1 << h;
        System.out.println("in exists");
        while (bit > 0){
            System.out.println("info:" + bit + "," + k);
           if ((bit & k) >> h == 1){ // 从第2位开始，每次判断每一位是0还是1，如果是1，是朝右
               node = node.right;
           } else { // 如果是0，朝左
               node = node.left;
           }
           h--;
           bit >>= 1;
        }
        boolean e = node != null;
        System.out.println(k + ":" + e);

        return e;
    }

    public static void main(String[] args) {

        TreeNode left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode right = new TreeNode(3, new TreeNode(6), null);
        TreeNode root = new TreeNode(1, left, right);
        CountCompleteTreeNodes inst = new CountCompleteTreeNodes();
        System.out.println(inst.countNodes(root));
    }
}
