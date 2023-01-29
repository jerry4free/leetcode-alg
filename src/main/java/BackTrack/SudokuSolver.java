package BackTrack;

import Util.Utils;

import java.util.*;

/**
 * 37 Sudoku Solver
 */
public class SudokuSolver {
    public static int n = 9;
    private int counter = 0;

    // 回溯方法
    // 第一版，时间耗时较长，通过了
    public void solveSudoku1(char[][] board) {

        // 将要填的表格标记序号，加入列表
        List<Integer> emptyList = new LinkedList<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == '.'){
                    emptyList.add(i * n + j);
                }
            }
        }

        traceback(board, emptyList, 0);
    }

    private boolean traceback(char[][] board, List<Integer> emptyList, int idx){
        // base case，如果都已经填完，则返回
        if (idx == emptyList.size()) {
            return true;
        }

        counter++;

        int i = emptyList.get(idx) / n;
        int j = emptyList.get(idx) % n;

        // 枚举当前board[i][j]的可选的有效值
        for (Character c: getValidNumbers(board, i, j)){
            board[i][j] = c;
//            System.out.println("board[" + i + "][" + j + "]:" + board[i][j]);
            if (traceback(board, emptyList, idx + 1)){
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }

    // 获取board[i][j]的能填充的有效值，对于每个空格需要4*9次数组访问
    private Set<Character> getValidNumbers(char[][] board, int i, int j){
        Set<Character> set = new HashSet<>();
        for (char c = 0; c < n; c++){
            set.add((char)('1' + c));
        }
        // check same row
        for (int k = 0; k < n; k++){
            set.remove(board[i][k]);
        }
        // check same column
        for (int k = 0; k < n; k++){
            set.remove(board[k][j]);
        }
        // check same sub-box
        for (int k = (i / 3) * 3; k < (i / 3 + 1) * 3; k++){
            for (int l = (j / 3) * 3; l < (j / 3 + 1) * 3; l++){
                set.remove(board[k][l]);
            }
        }

        return set;
    }

    private void showCallCnt(){
        System.out.println("traceback call count:" + counter);
    }

    private boolean[][] rowUsed; // 对于每一行i，column[i]标记9个数字是否已经被占用
    private boolean[][] columnUsed; // 对于每一列j，column[j]标记9个数字是否已经被占用
    private boolean[][][] subboxUsed; // 对于每一个subbox，subbox[i][j]标记其9个数字是否已经被占用

    public void solveSudoku(char[][] board) {
        rowUsed = new boolean[9][9];
        columnUsed = new boolean[9][9];
        subboxUsed = new boolean[3][3][9];

        // 将要填的表格标记序号，加入列表
        List<int[]> toDoList = new LinkedList<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == '.'){
                    toDoList.add(new int[]{i, j});
                } else {
                    int d = board[i][j] - '1'; // '1'转化成0，'2'转换成1，一次类推
                    rowUsed[i][d] = true;
                    columnUsed[j][d] = true;
                    subboxUsed[i/3][j/3][d] = true;
                }
            }
        }

        dfs(board, toDoList, 0);
    }

    private boolean dfs(char[][] board, List<int[]> toDoList, int idx){
        // base case，如果都已经填完，则返回
        if (idx == toDoList.size()) {
            return true;
        }

        int i = toDoList.get(idx)[0];
        int j = toDoList.get(idx)[1];

        // 枚举当前board[i][j]的可选的有效值,
        // 此处经过优化后，速度有所提升
        for (int d = 0; d < 9; d++){
            if (!rowUsed[i][d] && !columnUsed[j][d] && !subboxUsed[i/3][j/3][d]){
                board[i][j] = (char)(d + '1');
                rowUsed[i][d] = columnUsed[j][d] = subboxUsed[i/3][j/3][d] = true;
                if (dfs(board, toDoList, idx + 1)){
                    return true;
                }
                rowUsed[i][d] = columnUsed[j][d] = subboxUsed[i/3][j/3][d] = false;
                board[i][j] = '.';
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SudokuSolver inst = new SudokuSolver();
        char [][] board =
                {
                        {'5','3','.','.','7','.','.','.','.'},
                        {'6','.','.','1','9','5','.','.','.'},
                        {'.','9','8','.','.','.','.','6','.'},
                        {'8','.','.','.','6','.','.','.','3'},
                        {'4','.','.','8','.','3','.','.','1'},
                        {'7','.','.','.','2','.','.','.','6'},
                        {'.','6','.','.','.','.','2','8','.'},
                        {'.','.','.','4','1','9','.','.','5'},
                        {'.','.','.','.','8','.','.','7','9'}
                };
        inst.solveSudoku(board);
        Utils.show2D(board);
        inst.showCallCnt();
    }
}
