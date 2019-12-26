package com.liupeng;

/**
 * Package: com.liupeng
 * Description：java volatile 可见性的验证
 * volatile可以保证可见性，防止指令重排，但是不保证原子性。
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 9:58 AM
 * Modified By:
 *
 */
import java.util.concurrent.*;
class MyData{
    //private int number=0;
    private volatile int number=0;
    public void addTo60(){
        this.number=60;
    }

    public int getNumber(){
        return this.number;
    }
}
public class test1 {
    public static void main(String[] args) {
        MyData myData=new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\tcome in");
            try{TimeUnit.SECONDS.sleep(3);} catch(Exception e){e.printStackTrace();}
            myData.addTo60();
        },"AAA").start();

        while(myData.getNumber()==0){

        }
        System.out.println(Thread.currentThread().getName()+"\tmisson is over");
    }

}
