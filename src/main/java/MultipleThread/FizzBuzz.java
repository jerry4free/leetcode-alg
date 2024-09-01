package MultipleThread;

import java.util.concurrent.*;
import java.util.function.IntConsumer;

/**
 * 1195. 交替打印字符串
 */
class FizzBuzz {
    private int n;
    private Semaphore fizzS;
    private Semaphore buzzS;
    private Semaphore fizzBussS;
    private Semaphore nS;

    public FizzBuzz(int n) {
        this.n = n;
        this.fizzS = new Semaphore(0);
        this.buzzS = new Semaphore(0);
        this.fizzBussS = new Semaphore(0);
        this.nS = new Semaphore(1); // 首先是number
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            // 注意：15倍数不能获取fizzS信号量，否则会跟fizzBuzz重复获取导致阻塞，程序无法结束，最终超时
            if (i % 15 == 0) {
                continue;
            }
            fizzS.acquire();  // 获取对应的信号量fizzS
            printFizz.run();
            release(i+1);
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 15 == 0) {
                continue;
            }
            buzzS.acquire();
            printBuzz.run();
            release(i+1);
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            fizzBussS.acquire();
            printFizzBuzz.run();
            release(i+1);
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++){
            if (i % 3 != 0 && i % 5 != 0) {
                nS.acquire();
                printNumber.accept(i);
                release(i+1);
            }
        }
    }

    private void release(int i) {
        if (i % 15 == 0){
            fizzBussS.release();
        } else if (i % 5 == 0){
            buzzS.release();
        } else if (i % 3 == 0) {
            fizzS.release();
        } else {
            nS.release();
        }
    }

    public static void main(String[] args) throws Exception{
        FizzBuzz inst = new FizzBuzz(15);

        Runnable printFizz = () -> System.out.println("fizz");
        Runnable printBuzz = () -> System.out.println("buzz");
        Runnable printFizzBuzz = () -> System.out.println("fizzbuzz");
        IntConsumer printNumber = System.out::println;

        // 创建线程池
        ExecutorService es = Executors.newFixedThreadPool(4);

        // run task
        es.execute(() -> {
            try {
                inst.fizz(printFizz);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        es.execute(() -> {
            try {
                inst.buzz(printBuzz);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        es.execute(() -> {
            try {
                inst.fizzbuzz(printFizzBuzz);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        es.execute(() -> {
            try {
                inst.number(printNumber);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        // 关闭线程池
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);
    }
}
