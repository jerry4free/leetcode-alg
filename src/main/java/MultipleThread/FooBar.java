package MultipleThread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class FooBar {

    private int n;
    // 当前有1个许可证，不是说容量只能有1个许可证。release()N次后，可以有超过N个的许可证
    final Semaphore foo = new Semaphore(1);
    final Semaphore bar = new Semaphore(0);

    private BlockingDeque<Integer> fooQueue = new LinkedBlockingDeque<>();
    private BlockingDeque<Integer> barQueue = new LinkedBlockingDeque<>();
    // AtomicBoolean不行，因为不会阻塞
//    AtomicBoolean flag = new AtomicBoolean(false);

    public FooBar(int n) {
        this.n = n;
        fooQueue.add(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.acquire(); // may block
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.release(); // 不会阻塞
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

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

//        AtomicBoolean ff = new AtomicBoolean(true);
//        System.out.println(ff);

        /*
        PrintBar printBar = new PrintBar();
        PrintFoo printFoo = new PrintFoo();

        FooBar fooBar = new FooBar(3);
        try {
            fooBar.foo(printFoo);
            fooBar.bar(printBar);
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    }
}
