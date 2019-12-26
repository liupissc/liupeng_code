package com.liupeng;

/**
 * Package: com.liupeng
 * Description：自旋锁演示(while循环+CAS)
 * * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 4:29 PM
 * Modified By:
 */
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
    AtomicReference<Thread> atomicReference=new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " come in");
        while (!atomicReference.compareAndSet(null, thread)) {
            System.out.println(Thread.currentThread().getName() + " resource locked,try doing something else");
        }
    }
    public void myUnLock(){
        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " invoke unlock");
        atomicReference.compareAndSet(thread,null);

    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo=new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            try{TimeUnit.SECONDS.sleep(2);} catch(Exception e){e.printStackTrace();}
            spinLockDemo.myUnLock();
        },"a1").start();

        try{TimeUnit.SECONDS.sleep(1);} catch(Exception e){e.printStackTrace();}
        new Thread(() -> {
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        },"a2").start();
    }

}
