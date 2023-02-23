package ArrayX;

/**
 * 807. Max Increase to Keep City Skyline
 */
public class MaxIncreasetoKeepCitySkyline {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[] row  = new int[n];
        int[] column = new int[n];

        // caculate
        for (int i = 0; i < n; i++){
            int c = 0;  // max for ith row
            int r = 0;  // max for ith column
            for (int j = 0; j < n; j++){
                c = Math.max(grid[i][j], c);
                r = Math.max(grid[j][i], r);
            }
            column[i] = c;
            row[i] = r;
        }

        int ret = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                ret += Math.min(row[i], column[j]) - grid[i][j];
            }
        }

        return ret;
    }
}
