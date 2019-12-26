package com.liupeng;

/**
 * Package: com.liupeng
 * Description：ABA issue and solution
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 2:01 PM
 * Modified By:
 */
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    static AtomicReference<Integer> atomicReference=new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {
        System.out.println("****************以下是ABA问题的产生*****************");
        new Thread(() -> {
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(() -> {
            try{TimeUnit.SECONDS.sleep(1);} catch(Exception e){e.printStackTrace();}
            System.out.println(atomicReference.weakCompareAndSet(100,2019)+" "+atomicReference.get());
        },"t2").start();

        try{TimeUnit.SECONDS.sleep(2);} catch(Exception e){e.printStackTrace();}
        System.out.println("****************以下是ABA问题的解决*****************");

        new Thread(() -> {
            int stamp=atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+" 第一次版本号:"+stamp);
            try{TimeUnit.SECONDS.sleep(1);} catch(Exception e){e.printStackTrace();}

            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+" 第二次版本号:"+atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+" 第三次版本号:"+atomicStampedReference.getStamp());

        },"t3").start();

        new Thread(() -> {
            int stamp=atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+" 第一次版本号:"+stamp);
            try{TimeUnit.SECONDS.sleep(3);} catch(Exception e){e.printStackTrace();}

            boolean result=atomicStampedReference.compareAndSet(100,2019,stamp,stamp+1);

            System.out.println(Thread.currentThread().getName()+" 更新结果:"+result);
            System.out.println(Thread.currentThread().getName()+" current stamp:"+atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName()+" current value:"+atomicStampedReference.getReference());

        },"t4").start();
    }
}
