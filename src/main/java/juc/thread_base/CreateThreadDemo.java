package juc.thread_base;

/**
 * 创建线程的几种方式
 */
public class CreateThreadDemo {

    public  static class  MyThread extends Thread{
        @Override
        public void run() {
            //todo
            System.out.println("my thread running!");
        }
    }

    public  static void runThread(){
        MyThread t = new MyThread();
        t.start();
    }

    public static void main(String[] args) {

    }



}
