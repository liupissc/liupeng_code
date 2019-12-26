package com.liupeng;

/**
 * Package: com.liupeng
 * Description：
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 10:25 PM
 * Modified By:
 */
import java.util.concurrent.*;
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("***************召唤神龙");
        });


        for (int i = 1; i <=7 ; i++) {
            final int tempInt=i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+" 收集到第 "+tempInt+" 颗龙珠");
                try {
                    cyclicBarrier.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();

        }
    }
}
