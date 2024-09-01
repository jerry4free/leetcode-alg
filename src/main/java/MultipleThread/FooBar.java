package MultipleThread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1115. 交替打印 FooBar
 */
public class FooBar {

    private int n;
    // 方法1：Semaphore
    // 注意：当前有1个许可证，不是说容量只能有1个许可证。release()N次后，可以有超过N个的许可证
    final Semaphore foo = new Semaphore(1);
    final Semaphore bar = new Semaphore(0);

    // 方法2：阻塞队列
    // 队列为空时，take会阻塞。
    private BlockingDeque<Integer> fooQueue = new LinkedBlockingDeque<>();
    private BlockingDeque<Integer> barQueue = new LinkedBlockingDeque<>();
    // AtomicBoolean不行，因为不会阻塞
    // CountDownLatch不行，因为是计数只能使用一次。

    // 方法3：ReentrantLock + volatile变量，本质就是公平锁和一个共享变量作为开关
    private ReentrantLock lock = new ReentrantLock(true); // 公平锁
    private volatile boolean fooExec = true;

    public FooBar(int n) {
        this.n = n;
        fooQueue.add(0);
    }

    public void foo3(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n;) {
            try {
                lock.lock();
                if (fooExec) {
                    printFoo.run();
                    i++;
                    fooExec = false;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar3(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n;) {
            try {
                lock.lock();
                if (!fooExec) {
                    printBar.run();
                    i++;
                    fooExec = true;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void foo2(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.acquire(); // may block
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.release(); // 不会阻塞
        }
    }

    public void bar2(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            bar.acquire(); // bar许可证-1，may block
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.release(); // 不会阻塞
        }
    }

    public void foo1(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            fooQueue.take(); // may block
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            barQueue.add(0); // 容量很大，不会阻塞
        }
    }

    public void bar1(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            barQueue.take(); // may block
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fooQueue.add(0); // 不会阻塞
        }
    }


    public static void main(String[] args) {
        final Semaphore bar = new Semaphore(0);
        try {
            System.out.println("before release");
            System.out.println(bar.availablePermits());
            bar.release();
            bar.release();
            System.out.println(bar.availablePermits());

            System.out.println("before acquire");
            bar.acquire();
            System.out.println(bar.availablePermits());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
