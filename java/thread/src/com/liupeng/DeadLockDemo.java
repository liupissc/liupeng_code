package com.liupeng;

/**
 * Package: com.liupeng
 * Description：死锁是指两个或两个以上线程在执行过程中
 * 因争夺资源而造成的互相等待的现象，若无外力干涉，它们将无法推进下去
 * Author: liupissc@cn.ibm.com
 * Date:  12/28/2019 10:08 PM
 * Modified By:
 */
import java.util.concurrent.*;
class HoldThreadLock implements  Runnable{
    private String lockA;
    private String lockB;

    public HoldThreadLock(String lockA,String lockB){
        this.lockA=lockA;
        this.lockB=lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+" 自己持有"+lockA+" 尝试获得 "+lockB);
            try{TimeUnit.SECONDS.sleep(2);} catch(Exception e){e.printStackTrace();}

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+" 自己持有"+lockB+" 尝试获得 "+lockA);
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";

        new Thread(new HoldThreadLock(lockA,lockB),"AAA").start();
        new Thread(new HoldThreadLock(lockB,lockA),"BBB").start();

    }
}
