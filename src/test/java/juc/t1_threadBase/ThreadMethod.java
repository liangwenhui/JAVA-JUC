package juc.t1_threadBase;

import org.junit.Test;

/**
 * sleep
 * yield
 * join
 */
public class ThreadMethod {

    /**
     * sleep 让当前线程睡眠，在设定的时间后自动唤醒。期间一直占用cpu
     *
     * @throws InterruptedException
     */
    @Test
    public void testSleep() throws InterruptedException {
        Thread t = new Thread(()->{
            for(int i=0;i<5;i++){
                System.out.println(i);
                try {
                    Thread.sleep(200);
                    //TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.join();
    }

    /**
     * 当前线程等待join的线程执行完成，在这之前一直阻塞主
     * @throws InterruptedException
     */
    @Test
    public void testJoin() throws InterruptedException {
        Thread t1 = new Thread(()->{
            System.out.println("t1");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{

            try {
                t1.join();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });


        t1.start();
        t2.start();
        t2.join();


    }

    /**
     * 调用yield线程立即让出cpu，
     * 但是也会立即争夺cpu
     */
    @Test
    public void testYield() throws InterruptedException {
        Object o = new Object();
        Thread t1 = new Thread(()->{
            for(int i=0;i<100;i++){
                synchronized (o){
                    System.out.println("A   "+i);
                    if((i&10)==0){
                        Thread.yield();
                    }
                }

            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<100;i++){
                synchronized (o){
                    System.out.println("B   "+i);
                    if((i%10)==0){
                        Thread.yield();
                    }
                }

        }});

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }



}
