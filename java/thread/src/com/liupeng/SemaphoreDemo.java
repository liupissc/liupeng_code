package com.liupeng;

/**
 * Package: com.liupeng
 * Description：
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 10:42 PM
 * Modified By:
 */
import java.util.concurrent.*;
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);   //模拟3个停车位
        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" 抢到车位");
                    try{TimeUnit.SECONDS.sleep(3);} catch(Exception e){e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName()+" 停车3秒后离开车位");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
    
    
}
