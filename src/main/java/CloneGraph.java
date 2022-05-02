import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 133. 克隆图
 */
public class CloneGraph {
    Node[] cache;
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // 前提：是一个连通图，所以一次深度优先搜索就可以遍历完，即：采用返回Node的递归就可以完成
    // 重要：返回很重要
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        if (cache == null){
            cache = new Node[101];
        }

        // 存在，则通过cache查找，直接返回很重要，这是到栈顶了，从而避免在连通图中进入无限制的循环
        // 这是base case
        if (cache[node.val] != null) {
            return cache[node.val];
        }

        // 不存在，创建节点。遍历每个邻接节点，克隆并加入到邻接列表里
        Node destNode = new Node(node.val);
        cache[node.val] = destNode;
        for (Node w: node.neighbors) {
            destNode.neighbors.add(cloneGraph(w));
        }
        return destNode;
    }

    public Node cloneGraph2(Node node) {
        if (node == null) {
            return node;
        }

        if (cache == null) {
            cache = new Node[101];
        }

        // 队列里存储的是旧的节点
        Queue<Node> q = new LinkedList<Node>();
        // cache里存储的是新创建的节点
        Node root = new Node(node.val);
        cache[root.val] = root;
        // 加入开始节点
        q.add(node);

        while (!q.isEmpty()) {
            Node v_old = q.remove();
            // 找出新节点
            Node v_new = cache[v_old.val];
            for (Node w_old: v_old.neighbors){
                if (cache[w_old.val] == null){
                    // 如果没有访问过, 则创建节点，同时将新节点加入缓存
                    // 将旧的节点加到队列中
                    Node copy = new Node(w_old.val);
                    cache[copy.val] = copy;
                    v_new.neighbors.add(copy);
                    q.add(w_old);
                } else {
                    // 创建过，直接找到加入邻接表
                    v_new.neighbors.add(cache[w_old.val]);
                }
            }
        }
        return root;
    }

}
