package BackTrack;

import java.util.*;

/**
 * 93. Restore IP Addresses
 */
public class RestoreIPAddresses {
    /**
     * 由于要找到所有的有效IP地址，搜索所有可能解，分4步，每一步都有3种选择，这种一般可以通过回溯的方法来解决
     * 递归的深度搜索所有可能解，
     */
    public List<String> restoreIpAddresses(String s) {
        List<List<String>> ret = new ArrayList<>();
        List<String> path = new ArrayList<>();

        dfs(0, ret, path, s);

        List<String> ans = new ArrayList<>();
        for (List<String> ip: ret){
            ans.add(String.join(".", ip));
        }
        return ans;
    }

    private void dfs(int start, List<List<String>> ret, List<String> path, String s){
        // path中已经够4个数字了
        if (path.size() == 4){
            // 如果开始下标刚好越界，说明s遍历完了，符合条件，加入到结果列表中
            if (start == s.length()){
                ret.add(new ArrayList<>(path));
            }
            // 剪枝：如果没有到结尾，则忽略
            return;
        }


        for (int i = start; i < s.length(); i++){
            String item = s.substring(start, i + 1); // 由于是substring是左闭右开，右边界+1
            // 前导0不考虑, 这一层后面就不考虑
            if (i > start && s.charAt(start) == '0'){
                break;
            }

            // 超过255, 这一层后面就不考虑
            if (Integer.parseInt(item) > 255){
                break;
            }
            path.add(item);
            dfs(i + 1, ret, path, s);  // 下一次的下标就是i+1
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        RestoreIPAddresses inst = new RestoreIPAddresses();
        List<String> ret = inst.restoreIpAddresses("25525511135");
        for (String ip: ret){
            System.out.println(ip);
        }
        inst.restoreIpAddresses("0000");
        inst.restoreIpAddresses("1111");
    }
}
