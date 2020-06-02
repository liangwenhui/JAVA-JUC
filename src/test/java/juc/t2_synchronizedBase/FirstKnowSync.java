package juc.t2_synchronizedBase;

import org.junit.Test;

public class FirstKnowSync {

    public static Object o = new Object();
    public static int count = 0;

    @Test
    public void add() throws InterruptedException {

       AddWorker w1 = new AddWorker();
       AddWorker w2 = new AddWorker();
       AddWorker w3 = new AddWorker();
       w1.start();
       w2.start();
       w3.start();

       w1.join();
       w2.join();
       w3.join();

        System.out.println(30000);
        System.out.println("-------"+FirstKnowSync.count);
        FirstKnowSync.count=0;
        AddWorkerSync aw1 = new AddWorkerSync();
        AddWorkerSync aw2 = new AddWorkerSync();
        AddWorkerSync aw3 = new AddWorkerSync();
        aw1.start();
        aw2.start();
        aw3.start();

        aw1.join();
        aw2.join();
        aw3.join();
        System.out.println("-------"+FirstKnowSync.count);
    }





}
class AddWorkerSync extends Thread{

    @Override
    public void run() {
        Object lock = FirstKnowSync.o;
        for(int i=0;i<10000;i++){
            synchronized (lock){
                FirstKnowSync.count++;
            }
        }
    }
}

class AddWorker extends Thread{
    @Override
    public void run() {
        for (int i=0;i<10000;i++){
            FirstKnowSync.count++;
        }
    }
}