package com.liupeng;

/**
 * Package: com.liupeng
 * Description：可重入锁绑定多个condition,实现精确唤醒
 * Requirement: 多线程之间顺序调用A->B->C
 * A打印5次，B打印5次，C打印5次
 * 紧接着
 * A打印5次，B打印5次，C打印5次
 * ....
 * 来10轮
 * Author: liupissc@cn.ibm.com
 * Date:  12/27/2019 10:57 AM
 * Modified By:
 */

import com.sun.org.apache.bcel.internal.generic.LoadClass;
import sun.util.resources.cldr.ka.LocaleNames_ka;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum ThreadName{
    A(1,"A"),B(2,"B"),C(3,"C");

    private int threadCode;
    private String threadName;

    ThreadName(int threadCode,String threadName){
        this.threadCode=threadCode;
        this.threadName=threadName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public int getThreadCode() {
        return threadCode;
    }

    public void setThreadCode(int threadCode) {
        this.threadCode = threadCode;
    }

    public static ThreadName foreach(int index){
        ThreadName[] threadNames=ThreadName.values();
        for (ThreadName element:threadNames) {
            if (index==element.getThreadCode()){
                return element;
            }
        }
        return  null;
    }
}

class ShareResource{
    private int number = 1;  //A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();

    public void print(int threadNumber){
        Condition cwait=lock.newCondition();
        Condition csignal=lock.newCondition();
        int targetNumber=0;

        lock.lock();
        try{
            while (number!=threadNumber){
                switch (threadNumber){
                    case 1:
                        cwait=c1;
                        break;
                    case 2:
                        cwait=c2;
                        break;
                    case 3:
                        cwait=c3;
                        break;
                }
                cwait.await();
            }
            switch (threadNumber){
                case 1:
                    csignal=c2;
                    targetNumber=2;
                    break;
                case 2:
                    csignal=c3;
                    targetNumber=3;
                    break;
                case 3:
                    csignal=c1;
                    targetNumber=1;
                    break;
            }
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
            number=targetNumber;
            csignal.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ReentrantLockWithMultiConditionDemo {
    public static void main(String[] args) {
        ShareResource shareResource=new ShareResource();
        for (int i = 1; i <=10 ; i++) {

            for (int j = 1; j <=3 ; j++) {
                final int intTempJ=j;
                new Thread(() -> {
                    shareResource.print(intTempJ);
                }, ThreadName.foreach(j).getThreadName()).start();
            }

        }

    }
}
