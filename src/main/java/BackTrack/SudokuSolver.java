package BackTrack;

import Util.Utils;

import java.util.*;

/**
 * 37 Sudoku Solver
 */
public class SudokuSolver {
    public static int n = 9;

    // 第一次写，时间耗时较长，不过通过了
    public void solveSudoku(char[][] board) {

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
        int i = emptyList.get(idx) / n;
        int j = emptyList.get(idx) % n;

        // 枚举当前board[i][k]的可选的有效值
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
    }
}
