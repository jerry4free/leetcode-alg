package StringX;

/**
 * 6. Zigzag Conversion
 */
public class ZigzagConversion {

    // 每个字符在Z字形对应的行索引先增大，再缩小，行索引上下摆动，可以模拟这个过程
    public String convert(String s, int numRows) {
        StringBuilder[] list = new StringBuilder[numRows];
        if (numRows == 1){
            return s;
        }

        // 注意数组list的元素是StringBuilder，需要初始化，否则就是null
        for (int i = 0; i < numRows; i++){
            list[i] = new StringBuilder();
        }

        int i = 0;
        int step = 1;
        for (char c: s.toCharArray()){
            list[i].append(c);
            i += step;

            if (i == numRows - 1 || i == 0){ // 遇到头尾，那么下次换方向，即step取反
//                System.out.println("reverse next time");
                step *= -1;
            }
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder sb: list){
            ret.append(sb);
        }
        return new String(ret);
    }

    public static void main(String[] args) {
        ZigzagConversion inst = new ZigzagConversion();
        System.out.println("expected: PAHNAPLSIIGYIR, real: " + inst.convert("PAYPALISHIRING", 3));
        System.out.println("expected: AB, real: " + inst.convert("AB", 1));
    }
}
