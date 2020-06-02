package juc.t1_threadBase;

import juc.thread_base.CreateThreadDemo;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * 创建线程的几种方式
 */
public class CreateThread {



    public  static class  MyThread extends Thread{
        @Override
        public void run() {
            //todo
            System.out.println("my thread running!");
        }
    }
    @Test
    public   void runThread() throws InterruptedException {
        CreateThreadDemo.MyThread t = new CreateThreadDemo.MyThread();
        t.start();
        t.join();
    }


    public static class MyRunnable implements Runnable{

        @Override
        public void run() {
            //todo
            System.out.println("my runnable running");
        }
    }
    @Test
    public void runRunnable() throws InterruptedException{
        Thread t = new Thread(new MyRunnable());
        t.start();
        t.join();
    }


    public static class MyCallable implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            return 10010;
        }
    }
    @Test
    public void runCallable() throws ExecutionException, InterruptedException {
        MyCallable c = new MyCallable();
        FutureTask<Integer> f = new FutureTask<>(c);
        Thread t = new Thread(f);
        t.start();
        System.out.println(f.get());
    }


    /**
     * start thread
     */
    @Test
    public void startThread() throws ExecutionException, InterruptedException {
        //
        Thread t1 = new Thread();
        t1.start();
        //
        Thread t2 = new Thread(()->{
            //do some things
            System.out.println("lm runable");
        });
        t2.start();
        //
        Thread t3 = new Thread(new FutureTask<String>(()->{
            System.out.println("cable");
            return "cable";
        }));
        t3.start();
        //

        ExecutorService es = Executors.newSingleThreadExecutor();

        es.submit(()->{
            System.out.println("es submit runnable");
        });

        Future ft = es.submit(() -> {return "fust";});
        System.out.println(ft.get());

        es.execute(()->{
            System.out.println("es execute runnable");
        });



    }

}
