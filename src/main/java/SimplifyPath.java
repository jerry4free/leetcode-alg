import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 71. Simplify Path
 */
public class SimplifyPath {

    private void update(Stack<String> stack, String word){
        if (word.equals("..")){
            if (!stack.isEmpty()){  //!!! 注意，stack.pop前要判空
                stack.pop();
            }
        } else if (!word.equals(".")){
            stack.push(word);
        }
    }

    public String simplifyPath(String path) {

        Stack<String> stack = new Stack<>();
        /*
        int n = path.length();
        int lastSlashIndex = 0;
        // 也可以使用split来简化
        for (int i = 0; i < n; i++){
            if (path.charAt(i) == '/'){
                // 如果//中间有超过1个字符
                if (i > (lastSlashIndex+1)){
                    String word = path.substring(lastSlashIndex+1, i);
                    update(stack, word);
                }
                // 连续的///
                lastSlashIndex = i;
            }
        }
        if (lastSlashIndex < (n - 1)){
            String word = path.substring(lastSlashIndex+1, n);
            update(stack, word);
        }
        */

        for(String word: path.split("/")){
            if (!word.equals("")){
                update(stack, word);
            }
        }

        // 遍历栈是反向的，需要反序拼接起来
        List<String> paths = new ArrayList<>();
        if (stack.isEmpty()){
            return "/";
        }
        while (!stack.isEmpty()){
            paths.add(stack.pop());
        }
        StringBuilder s = new StringBuilder();
        for (int i = paths.size() - 1; i >= 0; i--){
            s.append("/").append(paths.get(i));
        }
        return s.toString();
    }


    public static void main(String[] args) {
        SimplifyPath inst = new SimplifyPath();
        System.out.println(inst.simplifyPath("/home/"));
        System.out.println(inst.simplifyPath("/../"));
        System.out.println(inst.simplifyPath("/home//foo/"));
        System.out.println(inst.simplifyPath("/a/./b/../../c/"));
    }
}
