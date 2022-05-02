import java.util.ArrayList;
import java.util.List;

/**
 * 155. Min Stack
 */
public class MinStack {

    List<Integer> data = new ArrayList<>();
    List<Integer> minData = new ArrayList<>();
    public MinStack() {
    }


    public void push(int val){
        int length = data.size();
        if (minData.isEmpty()) {
            minData.add(val);
        } else {
            minData.add(val < minData.get(length-1) ? val: minData.get(length-1));
        }
        data.add(val);
    }

    public void pop() {
        int length = data.size();
        minData.remove(length-1);
        data.remove(length-1);
    }

    public int top(){
        int length = data.size();
        return data.get(length-1);
    }

    public int getMin(){
        int length = minData.size();
        return minData.get(length-1);
    }

    public static void main(String[] args){
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assert minStack.getMin() == -3;
        minStack.pop();
        assert minStack.top() == 0;
        assert minStack.getMin() == -2;

    }
}
