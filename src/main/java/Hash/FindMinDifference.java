package Hash;

import java.util.Collections;
import java.util.List;

/**
 * 539. 最小时间差
 */
public class FindMinDifference {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int len = timePoints.size();
        String[] pair = timePoints.get(0).split(":");
        int m = Integer.parseInt(pair[0]);
        int s = Integer.parseInt(pair[1]);
        int firstTs = m * 60 + s;
        int prevTs = firstTs;
        int min = 24 * 60;
        for (int i = 1; i < len; i++){
            pair = timePoints.get(i).split(":");
            m = Integer.parseInt(pair[0]);
            s = Integer.parseInt(pair[1]);
            int ts = m * 60 + s;
            min = Math.min(min, ts - prevTs);
            if (min == 0){
                return 0;
            }
            prevTs = ts;
        }
        min = Math.min(min, 24 * 60 - prevTs + firstTs);

        return min;
    }
}
