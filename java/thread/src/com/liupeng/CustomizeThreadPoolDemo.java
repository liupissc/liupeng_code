package com.liupeng;

/**
 * Package: com.liupeng
 * Description：自定义线程池的演示
 * * Author: liupissc@cn.ibm.com
 * Date:  12/28/2019 9:43 PM
 * Modified By:
 */
import java.util.concurrent.*;
public class CustomizeThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool=new ThreadPoolExecutor(
                2,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
            for (int i = 1; i <=10 ; i++) {
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
