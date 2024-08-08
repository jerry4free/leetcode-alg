package QueueX;

import java.util.Deque;
import java.util.ArrayDeque;

/**
 * 1670. 设计前中后队列
 */
class FrontMiddleBackQueue {

    Deque<Integer> front;
    Deque<Integer> back;
    //           front                       back
    // addFirst                addLast
    // removeFirst            removeLast
    public FrontMiddleBackQueue() {
        front = new ArrayDeque<>();
        back = new ArrayDeque<>();
    }

    public void pushFront(int val) {
        front.addFirst(val);
        balance();
    }

    public void pushMiddle(int val) {
        if (front.size() < back.size()) {
            front.addLast(val);
        } else {
            back.addFirst(val);
        }
    }

    private void balance() {
        if (front.size() > back.size()) {
            // ->
            back.addFirst(front.removeLast());
        } else if (back.size() > (front.size() + 1)){
            // <-
            front.addLast(back.removeFirst());
        }
    }

    public void pushBack(int val) {
        back.addLast(val);
        balance();
    }

    public int popFront() {
        int ret;
        if (front.size() > 0) {
            ret = front.removeFirst();
        } else if (back.size() > 0 ) {
            ret = back.removeFirst();
        } else {
            ret = -1;
        }
        balance();
        return ret;
    }

    private void show() {
        System.out.println(front.getFirst() + "," + back.getLast());
    }

    public int popMiddle() {
        if (front.size() == back.size() && front.size() > 0) {
            // 相等，移除前面
            return front.removeLast();
        } else if (back.size() > 0) {
            // 不等，移除后面
            return back.removeFirst();
        } else {
            return -1;
        }
    }

    public int popBack() {
        int ret;
        if (back.size() > 0) {
            ret = back.removeLast();
        } else if (front.size() > 0 ) {
            ret = front.removeLast();
        } else {
            ret = -1;
        }
        balance();
        return ret;
    }

    /**
     * Your FrontMiddleBackQueue object will be instantiated and called as such:
     * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
     * obj.pushFront(val);
     * obj.pushMiddle(val);
     * obj.pushBack(val);
     * int param_4 = obj.popFront();
     * int param_5 = obj.popMiddle();
     * int param_6 = obj.popBack();
     */

    public static void main(String[] args){
        FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
        obj.pushFront(1);
        obj.pushBack(2);
        obj.pushMiddle(3);
        obj.pushMiddle(4);

        int param_4 = obj.popFront();
        System.out.println(param_4); // 1
        int param_5 = obj.popMiddle();
        System.out.println(param_5); // 3
        int param_6 = obj.popMiddle();
        System.out.println(param_6); // 2
        int param_7 = obj.popBack();
        System.out.println(param_7); // 4
        int param_8 = obj.popFront();
        System.out.println(param_8); // -1
    }
}
