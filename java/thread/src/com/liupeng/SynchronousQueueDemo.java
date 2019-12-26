package com.liupeng;

/**
 * Package: com.liupeng
 * Description：
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 11:28 PM
 * Modified By:
 */
import java.util.concurrent.*;
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue=new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+" put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName()+" put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName()+" put 3");
                blockingQueue.put("3");
            }catch (Exception e){
                e.printStackTrace();
            }


        },"AAA").start();


        new Thread(() -> {
            try {
                try{TimeUnit.SECONDS.sleep(5);} catch(Exception e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+" "+blockingQueue.take());
                try{TimeUnit.SECONDS.sleep(5);} catch(Exception e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+" "+blockingQueue.take());
                try{TimeUnit.SECONDS.sleep(5);} catch(Exception e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+" "+blockingQueue.take());

            }catch (Exception e){
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
