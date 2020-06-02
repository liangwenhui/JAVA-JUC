package juc.t1_threadBase;

import org.junit.Test;

/**
 *
 *   O           -----------------------------
 *   |          |runnable                    |
 *   new ----->|    ready  <----> running   | ----> teminated
 *            |                            |
 *            ------------------------------

                        timedWaiting
 *
 *                       waiting
 *
 *                       blocked
 *
 *
 */


public class ThreadStatus {


    @Test
    public void testStatus(){
        Thread t = new Thread(()->{
            for(;;){

            }
        });
        System.out.println(t.getState());

        t.start();

        System.out.println(t.getState());
    }

@Test
    public void testStatus2() throws InterruptedException {
        Thread t = new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }) ;

        t.start();
    Thread.sleep(500);
    System.out.println(t.getState());
    t.join();
    System.out.println(t.getState());




    }




}
