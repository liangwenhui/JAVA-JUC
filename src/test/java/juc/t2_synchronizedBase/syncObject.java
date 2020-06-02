package juc.t2_synchronizedBase;



import org.junit.Test;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  1.public synchronized void method(){...}  == public void method(){synchronized(this){...}}
 *
 *  2.public void method(){
 *      synchronized(o){
 *          ...
 *      }
 *  }
 *
 */
public class syncObject {

@Test
    public void t1() throws IOException, InterruptedException {
        A a = new A();
        Thread t = new Thread(()->{
            for(int i=0;i<10000;i++){
                a.add();
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<10000;i++){
                a.add();
            }
        });
        t.start();t2.start();
        t.join();t2.join();
        System.out.println(a.c);

}







    @Test
    public void t2() throws IOException, InterruptedException {
        A a = new A();
        Thread t = new Thread(()->{
            for(int i=0;i<10000;i++){
                a.syncAdd();
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<10000;i++){
                a.syncAdd();
            }
        });
        t.start();t2.start();
        t.join();t2.join();
        System.out.println(a.c);
    }
    @Test
    public void t3() throws IOException, InterruptedException {
        A a = new A();
        Thread t = new Thread(()->{
            for(int i=0;i<10000;i++){
                a.syncAdd2();
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<10000;i++){
                a.syncAdd2();
            }
        });
        t.start();t2.start();
        t.join();t2.join();
        System.out.println(a.c);

    }

}



class A {
    Object o = new Object();
    public static int count = 0;
    public int c = 0;
    public void add(){
        c++;
    }

    public static void staticAdd(){
        count++;
    }
    public synchronized static void staticSyncAdd(){
        count++;
    }

    public synchronized void syncAdd(){
        c++;
    }

    public void syncAdd2(){
        synchronized (o){
            c++;
        }
    }

//    @Test
//    public void a(){
//        ArrayList<HashMap<String,Integer>> list = new ArrayList();
//        for(int i=0;i<10000000;i++){
//            list.add(new HashMap<String,Integer>(){{put("m1",1);put("m2",2);}});
//        }
//
//
//        HashMap<String,Integer> sum = new HashMap(){{
//            put("m1",0);
//            put("m2",0);
//        }};
//
//
//        int m1 = list.parallelStream().mapToInt(o -> o.get("m1")).sum();
//        int m2 = list.parallelStream().mapToInt(o -> o.get("m2")).sum();
//        sum.put("m1",m1);
//        sum.put("m2",m2);
//        System.out.println(sum);
//    }
//    @Test
//    public void a2(){
//        ArrayList<HashMap<String,Integer>> list = new ArrayList();
//        for(int i=0;i<10000000;i++){
//            list.add(new HashMap<String,Integer>(){{put("m1",1);put("m2",2);}});
//        }
//
//
//        HashMap<String,Integer> sum = new HashMap(){{
//            put("m1",0);
//            put("m2",0);
//        }};
//
//        AtomicInteger mm1 = new AtomicInteger();
//        AtomicInteger mm2 = new AtomicInteger();
//        list.parallelStream().forEach(o->{
//            int om1 =  o.get("m1");
//            int om2 =  o.get("m2");
//
//            mm1.addAndGet(om1);
//            mm2.addAndGet(om2);
//
//
//        });
//
//        sum.put("m1",mm1.intValue());
//        sum.put("m2",mm2.intValue());
//        System.out.println(sum);
//
//    }
//
//
//    @Test
//    public void a3(){
//        ArrayList<HashMap<String,Integer>> list = new ArrayList();
//        for(int i=0;i<10000000;i++){
//            list.add(new HashMap<String,Integer>(){{put("m1",1);put("m2",2);}});
//        }
//
//
//        HashMap<String,Integer> sum = new HashMap(){{
//            put("m1",0);
//            put("m2",0);
//        }};
//
//
//        int m1 = 0;
//        int m2 = 0;
//        for(HashMap<String,Integer> h : list){
//            m1+= h.get("m1");
//            m2+= h.get("m2");
//        }
//        sum.put("m1",m1);
//        sum.put("m2",m2);
//        System.out.println(sum);
//    }


}