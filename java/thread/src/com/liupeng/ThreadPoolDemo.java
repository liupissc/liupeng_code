package com.liupeng;

/**
 * Package: com.liupeng
 * Description：线程池
 * Author: liupissc@cn.ibm.com
 * Date:  12/28/2019 8:05 PM
 * Modified By:
 */
import java.util.concurrent.*;
public class ThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        //ExecutorService threadPool=Executors.newFixedThreadPool(5);  //执行长期的任务，性能好很多
        //ExecutorService threadPool=Executors.newSingleThreadExecutor();//一个任务一个任务执行的场景
        ExecutorService threadPool=Executors.newCachedThreadPool();//执行很多短期异步的小程序或负载较轻的服务器

        try {
            //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 1; i <=5 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" 办理业务");
                        //try{TimeUnit.SECONDS.sleep(1);} catch(Exception e){e.printStackTrace();}
                    }

                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
