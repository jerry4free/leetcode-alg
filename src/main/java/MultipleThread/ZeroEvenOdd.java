package MultipleThread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 1116. 打印零与奇偶数
 */
public class ZeroEvenOdd {
    private int n;
    // 方法1:阻塞队列
    BlockingDeque<Integer> zeroQ;
    BlockingDeque<Integer> evenQ;
    BlockingDeque<Integer> oddQ;

    // 方法2：锁 + condition + 全局volatile变量
    private Lock lock = new ReentrantLock();
    private Condition z = lock.newCondition();
    private Condition o = lock.newCondition();
    private Condition e = lock.newCondition();
    private volatile int flag = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.zeroQ = new LinkedBlockingDeque<>();
        this.evenQ = new LinkedBlockingDeque<>();
        this.oddQ = new LinkedBlockingDeque<>();
        this.zeroQ.add(0);
        z.signal();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++){
            zeroQ.take();
            printNumber.accept(0);
            if (i % 2 == 0) {
                oddQ.add(0);
            } else {
                evenQ.add(0);
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2){
            evenQ.take();
            printNumber.accept(i);
            zeroQ.add(0);
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2){
            oddQ.take();
            printNumber.accept(i);
            zeroQ.add(0);
        }
    }

    public void zero2(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++){
            try {
                lock.lock();
                if (flag != 0) {
                    z.await();  // 阻塞当前线程（同时释放锁）直到z条件被signal。
                }
                printNumber.accept(0);
                if (i % 2 == 1) {
                    flag = 2;
                    e.signal(); // 唤醒被e条件阻塞的线程
                } else {
                    flag = 1;
                    o.signal(); // 唤醒被o条件阻塞的线程
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void even2(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2){
            try {
                lock.lock();
                if (flag != 2) {  // TODO:为什么还需要判断flag，zero函数里直接唤醒e，所以不判断flag也可以吧，但是为什么去掉会超时？
                    e.await();  // 阻塞当前线程（同时释放锁）直到e被signal
                }
                printNumber.accept(i);
                flag = 0;
                z.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void odd2(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2){
            try {
                lock.lock();
                if (flag != 1) { // TODO:为什么还需要判断flag，zero函数里直接唤醒o，所以不判断flag也可以吧，但是为什么去掉会超时？
                    o.await();  // 阻塞当前线程（同时释放锁）直到o被signal
                }
                printNumber.accept(i);
                flag = 0;
                z.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
