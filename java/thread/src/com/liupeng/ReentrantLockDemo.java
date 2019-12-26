package com.liupeng;

/**
 * Package: com.liupeng
 * Description：可重入锁
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 3:07 PM
 * Modified By:
 */
import java.util.Locale;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements  Runnable{
    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+" invoke sendSMS()");
        sendEmail();
    }
    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName()+" ********invoke sendEmail()");
    }

    //=====================================================================================================
    Lock lock=new ReentrantLock();
    @Override
    public void run() {
        get();
    }
    public void get(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" invoke get()");
            set();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" ********invoke set()");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) throws Exception {
        Phone phone=new Phone();
        new Thread(() -> {
            phone.sendSMS();

        },"t1").start();

        new Thread(() -> {
            phone.sendSMS();
        },"t2").start();

        try{TimeUnit.SECONDS.sleep(1);} catch(Exception e){e.printStackTrace();}

        System.out.println();
        System.out.println();
        System.out.println();

        Thread t3=new Thread(phone,"t3");
        t3.start();

        Thread t4=new Thread(phone,"t4");
        t4.start();
    }
}
