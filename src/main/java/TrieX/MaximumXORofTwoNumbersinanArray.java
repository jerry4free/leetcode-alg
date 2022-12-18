package TrieX;

/**
 * 421. Maximum XOR of Two Numbers in an Array
 */
public class MaximumXORofTwoNumbersinanArray {
    public class TrieNode{
        TrieNode[] next;

        public TrieNode(){
            next = new TrieNode[2];
        }
    }

    public int findMaximumXOR(int[] nums) {
        // 构建trie树
        TrieNode root = new TrieNode();
        for (int n: nums){
            TrieNode curr = root;
            for (int i = 30; i >= 0; i--){
                if (((n >> i) & 1) == 1){
                    if (curr.next[1] == null){
                        curr.next[1] = new TrieNode();
                    }
                    curr = curr.next[1];
                } else {
                    if (curr.next[0] == null){
                        curr.next[0] = new TrieNode();
                    }
                    curr = curr.next[0];
                }
            }
        }

        // 枚举nums，对于每个nums[i]，在树中查找使其异或最大的那个数字nums[j]。
        int ret = 0;
        for (int n: nums){
            int x = 0;  // x 是以nums[i]为选择时，在trie树中能找到的另一个元素使二者异或的最大值
            TrieNode curr = root;
            for (int i = 30; i >= 0; i--){
                if (((n >> i) & 1) == 1){ // 第i为是1
                    if (curr.next[0] != null){
                        curr = curr.next[0];
                        x = (x << 1) + 1;
                    } else {
                        curr = curr.next[1];
                        x = x << 1;
                    }
                } else {  // 第i位是0
                    if (curr.next[1] != null){
                        curr = curr.next[1];
                        x = (x << 1) + 1;
                    } else {
                        curr = curr.next[0];
                        x = x << 1;
                    }

                }
            }
            ret = Math.max(ret, x);
        }
        return ret;
    }
}
