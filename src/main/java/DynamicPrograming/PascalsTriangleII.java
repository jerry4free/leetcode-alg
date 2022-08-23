package DynamicPrograming;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. Pascal's Triangle II
 */
public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
        // 第rowIndex层，有rowIndex+1个元素
        List<Integer> ret = new ArrayList<>(rowIndex + 1);
        ret.add(1);
        List<Integer> tmp = new ArrayList<>(rowIndex + 1);

        /**
         * 注意：List必须先Add，有了对应的下标元素之后，才能通过set来修改对应下标。因为set时会判断size。
         * capacity not equal size
         * 跟数组有所区别，数组可以设置
         */
        for (int i = 1; i <= rowIndex; i++){
            tmp.clear();
            for (int j = 0; j <= i; j++){
                if (j == 0 || j == i){
                    tmp.add(1);
                } else {
                    tmp.add(j, ret.get(j) + ret.get(j-1));
                }
            }
            // copy tmp to ret
            ret.clear();
            for (int j = 0; j <= i; j++){
                ret.add(tmp.get(j));
            }
        }

        return ret;
    }

    public static void show(List<Integer> rows){
        for(int i: rows){
            System.out.print(i + ",");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int[] nums = new int[3];
        PascalsTriangleII inst = new PascalsTriangleII();
        show(inst.getRow(3));
    }
}
