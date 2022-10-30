package TreeX;

import BinarySearch.TreeNode;

import java.util.*;

/**
 * 297.序列化与反序列化二叉树
 */
public class Codec {


    // DFS: 由于前序遍历是根节点优先，所以采用前序遍历
    public String serialize(TreeNode root) {
        if (root == null){
            return "null";
        }
        // 遍历时采用后序，即先遍历2个子节点，再遍历
        String left =  serialize(root.left);
        String right =  serialize(root.right);
//        System.out.println("==>" + root.val + "," + left + "," + right);
        // 拼接序列化结果时，采用前序的顺序
        return root.val + "," + left + "," + right;
    }

    public TreeNode deserialize(String data) {
        String[] strings = data.split(",", -1);
        return _deserialize(strings, new int[]{0});
//        return _deserialize(new ArrayList<>(Arrays.asList(strings)));
    }

    // 方法1：
    // 采用修改dataList的方式，每次删掉第一个
    private TreeNode _deserialize(List<String> dataList){
        if (dataList.get(0).equals("null")){
            dataList.remove(0);
            return null;
        }

        int val = Integer.parseInt(dataList.remove(0));
        TreeNode node = new TreeNode(val);
        node.left = _deserialize(dataList);
        node.right = _deserialize(dataList);
        return node;
    }

    // 方法2：
    // 采用数组存储遍历下标，由于数组时在堆上分配的, 而不是局部变量，类似一个递归遍历时的全局变量
    // 或者也可以采用一个类的成员变量，这样递归进行遍历也可以使用
    private TreeNode _deserialize(String[] dataArray, int[] index){
        if (dataArray[index[0]].equals("null")){
            index[0]++;
            return null;
        }

        int val = Integer.parseInt(dataArray[index[0]++]);
        TreeNode node = new TreeNode(val);
        node.left = _deserialize(dataArray, index);
        node.right = _deserialize(dataArray, index);
        return node;
    }


    // BFS
    public String serialize2(TreeNode root) {
        List<String> serList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null){
            return "";
        }
        queue.add(root);

        // 通过队列来进行层级遍历
        while (!queue.isEmpty()){
            for (int i = queue.size(); i > 0; i--){  // 取出当前层节点数，直到遍历完。
                TreeNode node = queue.poll();
                if (node != null){
                    // 下一层还有子节点，则将子节点加入队列
                    queue.add(node.left);
                    queue.add(node.right);
                    serList.add(String.valueOf(node.val));
                } else {
                    serList.add("null");
                }
            }
        }

        return String.join(",", serList);
    }

    public TreeNode deserialize2(String data) {
        if (data.equals("")){
            return null;
        }

        String[] strs = data.split(",", -1);
        int i = 0;

        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(strs[i++]));
        q.add(root);

        while (!q.isEmpty()){
            for (int j = q.size(); j > 0; j--){
                TreeNode parent = q.poll();
                if (parent != null){
                    // 左节点
                    TreeNode left;
                    if (!strs[i].equals("null")){
                        left = new TreeNode(Integer.parseInt(strs[i]));
                    } else {
                        left = null;
                    }
                    i++;
                    parent.left = left;
                    q.add(left);

                    // 右节点
                    TreeNode right;
                    if (!strs[i].equals("null")){
                        right = new TreeNode(Integer.parseInt(strs[i]));
                    } else {
                        right = null;
                    }
                    i++;

                    q.add(right);
                    parent.right = right;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {

        // construct a tree
        TreeNode left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        TreeNode root = new TreeNode(1, left, new TreeNode(5));

        Codec inst = new Codec();
        // serialize
        String treeString = inst.serialize2(root);
        System.out.println(treeString);

        // deserialize
        TreeNode deTreeNode = inst.deserialize2(treeString);
        System.out.println(inst.serialize2(deTreeNode));
    }
}