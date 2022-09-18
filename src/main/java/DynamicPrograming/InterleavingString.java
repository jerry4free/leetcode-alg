package DynamicPrograming;


/**
 * 97. Interleaving String
 * 剑指 Offer II 096. 字符串交织
 */
public class InterleavingString {
    /**
     * 假设f(i,j)表示s1的前i个字符（s1[0:i-1]）和s2的前j个字符（s2[0:j-1]）能否进行交织组成s3的前i+j个字符（s3[0:i+j-1]）,
     * 那么用一个二维数组存储状态值（boolean值）
     * 那么f(i,j)跟它前面状态的关系可以表示为：
     * 1. 如果s1[i-1]跟s3[i+j-1]相同，s1、s2能否交织成s3取决于s1的前i-1个字符、s2的前j个字符能否交织成s3
     * 2. 如果s2[j-1]跟s3[i+j-1]相同，s1、s2能否交织成s3取决于s2的前j-1个字符、s1的前i个字符能否交织成s3
     * 只要2种情况有一个能交织，f(i,j)就能交织
     *
     * 所以f(i,j)的状态转移方程就是
     * 1. f(i,j) = f(i-1,j) 当 s1[i-1] == s3[i+j-1], 或者
     * 2. f(i,j) = f(i,j-1) 当 s2[j-1] == s3[i+j-1]
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int l = s3.length();

        if (m+n != l){
            return false;
        }

        // 分析base case后，发现需要分析s1和s2分别是空时的边界情况，所以需要多加一行和一列
        boolean dp[][] = new boolean[m+1][n+1];
        // 由于空的s1和空的s2，可以组成空的s3
        dp[0][0] = true;

        // 注意f(i,j)表示的s1[0:i-1]和s2[0:j-1]能否交织形成s3[0:i+j-1], 所以参数是i时，表示的s1数组下标是i-1
        // 所以针对i=0和j=0的base case得处理, 真正的状态从1开始
        // j=0时，就是s2是空时，s1能形成s3,(处理的是状态的第一列)
        for (int i = 1; i <= m; i++){
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
        }

        // i=0时，就是s1是空时，s2能形成s3,(处理的是状态第一行)
        for (int j = 1; j <= n; j++){
            dp[0][j] = dp[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1);
        }

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1) )
                        || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1) );
            }
        }

        show2D(dp);

        return dp[m][n];
    }

    public static void show2D(boolean[][] n){
        for (boolean[] a : n){
            for (boolean i: a){
                System.out.print(i + ", ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        InterleavingString inst = new InterleavingString();
//        System.out.println(inst.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(inst.isInterleave("bacc", "aabcce", "abaacbccec"));
    }
}
