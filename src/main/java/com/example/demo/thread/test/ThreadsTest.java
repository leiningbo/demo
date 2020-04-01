package com.example.demo.thread.test;

import com.example.demo.thread.MyCallable;
import com.example.demo.thread.MyRunnable;
import com.example.demo.thread.MyThreads;
import org.junit.Test;

import java.util.concurrent.*;

public class ThreadsTest {
    @Test
    public void test() throws Exception {
        Thread t1 = new MyThreads();
        Thread t2 = new MyThreads();
        t1.start();
        t2.start();
        Thread.sleep(1000);

    }

    @Test
    public void test2() throws Exception{
        Runnable runnable = new MyRunnable();
        new Thread(runnable).start();
        new Thread(runnable).start();
        Thread.sleep(1000);
    }

    @Test
    public void test3() throws Exception{
        Callable<Integer> callable = new MyCallable();
        FutureTask<Integer> task1 = new FutureTask<>(callable);
        FutureTask<Integer> task2 = new FutureTask<>(new MyCallable());
        new Thread(task1).start();
        new Thread(task2).start();
        Integer num1 = task1.get();
        Integer num2 = task2.get();
        System.out.println("num1:"+num1);
        System.out.println("num2:"+num2);
    }


    @Test
    public void test4() throws Exception{
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Runnable runnable = new MyRunnable();
        pool.submit(runnable);
        pool.submit(runnable);
        pool.submit(runnable);
        pool.submit(runnable);
        Callable<Integer> callable = new MyCallable();
        Future<Integer> integerFuture = pool.submit(callable);
        System.out.println("integerFutrue:"+integerFuture.get());

        pool.shutdown();
    }


    /**
     * synchronied 和 ReentrantLock
     * 1) synchronized:可以用来同步方法和同步代码块。
     * 同步方法:给一个方法增加synchronized关键字，可以是静态方法(锁住整个类)也可以是非静态方法(不能是抽象方法)
     * 同步代码块:通过锁定一个指定的对象，来对同步代码块进行同步。
     * 同步是高开销操作，尽量少用同步方法，同步关键代码的代码块即可
     * 2) ReentrantLock:可重入锁， 代码通过lock()方法获取锁，但必须调用unlock()方法释放锁
     * @throws Exception
     */
    @Test
    public void test5() throws Exception{
        Runnable runnable = new MyRunnable();
        for (int i = 0; i < 20; i++) {
            new Thread(runnable).start();
        }
        Thread.sleep(30000);
    }

}
