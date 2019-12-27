package com.liupeng;

/**
 * Package: com.liupeng
 * Description：
 * Author: liupissc@cn.ibm.com
 * Date:  12/27/2019 9:24 AM
 * Modified By:
 */
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData              //资源类
{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() throws Exception {

        lock.lock();
        try {
            while (number != 0) {              //判断
            //if (number != 0) {
                //等待，不能生产
                condition.await();
            }
            number++;  //干活
            System.out.println(Thread.currentThread().getName() + " " + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws Exception {

        lock.lock();
        try {
            while (number == 0) {              //判断
            //if (number == 0) {              //判断
                //等待，不能生产
                condition.await();
            }
            number--;  //干活
            System.out.println(Thread.currentThread().getName() + " " + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ProdConsumer_TraditionalDemo  {
    public static void main(String[] args) {
        ShareData shareData=new ShareData();

        new Thread(() -> {
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.produce();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"AAA").start();

        new Thread(() -> {
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.consume();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"BBB").start();

        /*
        new Thread(() -> {
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.produce();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"CCC").start();

        new Thread(() -> {
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.consume();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"DDD").start();

        new Thread(() -> {
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.produce();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"EEE").start();

        new Thread(() -> {
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.consume();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"FFF").start();

        new Thread(() -> {
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.produce();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"GGG").start();

        new Thread(() -> {
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.consume();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"HHH").start();

         */


    }

}
