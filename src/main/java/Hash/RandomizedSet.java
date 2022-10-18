package Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomizedSet {

    HashMap<Integer, Integer> numToLoc;
    ArrayList<Integer> nums;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        // 存储val和其在arrayList中的下标
        numToLoc = new HashMap<>();
        nums = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (numToLoc.containsKey(val)){
            return false;
        }

        nums.add(val);
        numToLoc.put(val, nums.size()-1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!numToLoc.containsKey(val)){
            return false;
        }

        int idx = numToLoc.remove(val);
        // 要删除的元素是最后一个
        if (idx == nums.size() - 1){
            nums.remove(nums.size()-1);
        } else {
            // 不是最后一个，那么将最后一个元素覆盖到当前下标
            int lastVal = nums.remove(nums.size()-1);
            nums.set(idx, lastVal);
            // 同时将最后一个元素的下标更新
            numToLoc.put(lastVal, idx);
        }
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        // nextInt是从0开始的, 左闭右开
        int idx = new Random().nextInt(nums.size());
        return nums.get(idx);
    }

    public static void main(String[] args) {

    }
}
