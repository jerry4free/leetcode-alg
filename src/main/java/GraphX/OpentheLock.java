package GraphX;

import java.util.*;

/**
 * 752. Open the Lock
 */
public class OpentheLock {

    Set<String> dead;

    /**
     * 无向图的最短路径问题
     *
     * 双向BFS
     */
    public int openLock(String[] deadends, String target) {
        dead = new HashSet<>(Arrays.asList(deadends));
        if (dead.contains(target) || dead.contains("0000")){
            return -1;
        }
        if (target.equals("0000")){
            return 0;
        }

        Queue<String> q1 = new LinkedList<>();
        Set<String> visited1 = new HashSet<>();
        q1.add("0000");
        visited1.add("0000");

        Queue<String> q2 = new LinkedList<>();
        Set<String> visited2 = new HashSet<>();
        q2.add(target);
        visited2.add(target);

        int ret = 1;
        boolean found;
        while (!q1.isEmpty() && !q2.isEmpty()){
            if (q1.size() > q2.size()){
                found = bfs(q2, visited2, visited1);
            } else {
                found = bfs(q1, visited1, visited2);
            }
            if (found){
                return ret;
            }

            ret++;
        }

        return -1;
    }

    /**
     *
     * @param q1: 长度较小的队列
     * @param visited1：长度较小这一端的visited
     * @param visited2: 另一端的visited
     * @return： 两端是否相交
     */
    private boolean bfs(Queue<String> q1, Set<String> visited1, Set<String> visited2){
        int sz = q1.size();
        for (int i = 0; i < sz; i++){
            String curr = q1.poll();
            for (String next: getAdj(curr, visited1)){
                if (visited2.contains(next)){
                    return true;
                } else {
                    q1.add(next);
                    visited1.add(next);
                }
            }
        }
        return false;
    }

    // 单向BFS
    public int openLock2(String[] deadends, String target) {
        dead = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        if (dead.contains(target) || dead.contains("0000")){
            return -1;
        }
        if (target.equals("0000")){
            return 0;
        }

        Queue<String> q = new LinkedList<>();
        visited.add("0000");
        q.add("0000");

        int ret = 0;
        while (!q.isEmpty()){
            // 一层一层遍历
            int size = q.size();
            for (int i = 0; i < size; i++){
                String curr = q.poll();
                for (String next: getAdj(curr, visited)){
                    if (target.equals(next)){
                        return ret + 1;
                    } else {
                        visited.add(next);
                        q.add(next);
                    }
                }
            }

            ret++;
        }

        return -1;
    }

    private void show(Iterable<String> adj){
        for (String v: adj){
            System.out.print("," + v);
        }
        System.out.println();
    }

    private char getPrev(char c){
        if (c == '0') {
            return '9';
        } else {
            return (char) (c - 1);
        }
    }

    private char getNext(char c){
        if (c == '9'){
            return '0';
        } else {
            return (char) (c + 1);
        }
    }

    private List<String> getAdj(String word, Set<String> visited) {
        List<String> adj = new ArrayList<>();

        char[] chars = word.toCharArray();
        String next;
        for (int i = 0; i < 4; i++) {
            char t = chars[i];

            chars[i] = getPrev(t);
            next = new String(chars);
            // 注意用visited来比较已访问的，否则同一个路径上可能会有多个相同词
            if (!dead.contains(next) && !visited.contains(next)) {
                adj.add(next);
            }

            chars[i] = getNext(t);
            next = new String(chars);
            if (!dead.contains(next) && !visited.contains(next)) {
                adj.add(next);
            }

            chars[i] = t;
        }

        return adj;
    }

    public static void main(String[] args) {
        OpentheLock inst = new OpentheLock();

        String[] deadends = new String[]{"0201","0101","0102","1212","2002"};
        System.out.println("expected: 6, real:" + inst.openLock(deadends, "0202"));

        String[] deadends1 = new String[]{};
        System.out.println("expected: 3, real:" + inst.openLock(deadends1, "0101"));
    }

}
