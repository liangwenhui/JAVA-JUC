package juc.lock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T1_ReentrantLock {

    Lock lock = new ReentrantLock();
    public void t1() throws InterruptedException {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName());

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    @Test
    public void t() throws InterruptedException {
            T1_ReentrantLock t1_reentrantLock = new T1_ReentrantLock();
            Thread t1 = new Thread(()->{
                try {
                    t1_reentrantLock.t1();
                    TimeUnit.SECONDS.sleep(3);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });Thread t2 = new Thread(()->{
                try {
                    t1_reentrantLock.t1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();
    }


}
