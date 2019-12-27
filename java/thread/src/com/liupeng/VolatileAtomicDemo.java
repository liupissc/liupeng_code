package com.liupeng;

/**
 * Package: com.liupeng
 * Description：java volatile 不保证原子性的验证,以及使用AtomicInteger解决方案
 * volatile可以保证可见性，防止指令重排，但是不保证原子性。
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 9:58 AM
 * Modified By:
 *
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData2{

    private volatile int number=0;
    private AtomicInteger atomicInteger=new AtomicInteger(0);
    public void addPlusPlus(){
        this.number++;
    }

    public void  addAtomicInteger(){
        this.atomicInteger.getAndIncrement();
    }

    public int getNumber(){
        return this.number;
    }

    public int getAtomiceNumber(){
        return this.atomicInteger.get();
    }
}

public class VolatileAtomicDemo {
    public static void main(String[] args) {
        MyData2 myData=new MyData2();

        for (int i = 1; i <=20 ; i++) {
            new Thread(() -> {
                for (int j = 1; j <=1000 ; j++) {
                    //myData.addPlusPlus();
                    myData.addAtomicInteger();   //保证原子性
                }
            },String.valueOf(i)).start();
        }




        while(Thread.activeCount()>2){
            Thread.yield();//main线程礼让
        }
        //System.out.println(Thread.currentThread().getName()+" finally number is "+myData.getNumber());
        System.out.println(Thread.currentThread().getName()+" finally number is "+myData.getAtomiceNumber());
    }

}
