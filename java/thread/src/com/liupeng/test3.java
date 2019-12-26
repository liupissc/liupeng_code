package com.liupeng;

/**
 * Package: com.liupeng
 * Description：指令重排的例子
 * volatile可以保证可见性，防止指令重排，但是不保证原子性。
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 11:28 AM
 * Modified By:
 */
import java.util.concurrent.*;
class ReSortDemo{
    int a=0;
    boolean flag=false;

    public void  method1(){
        a=1;            //语句1
        flag=true;      //语句2
    }

    public void  method2(){
        if (flag){
            //a=a+5;
            System.out.println(Thread.currentThread().getName()+" result value is "+a);
        }

    }

    public void mySort() {

        int x = 11; // 語句1
        try{TimeUnit.SECONDS.sleep(2);} catch(Exception e){e.printStackTrace();}
        int y = 12; // 語句2
        try{TimeUnit.SECONDS.sleep(2);} catch(Exception e){e.printStackTrace();}
        x = x + 5; // 語句3
        try{TimeUnit.SECONDS.sleep(2);} catch(Exception e){e.printStackTrace();}
        y = x * x; // 語句4
        try{TimeUnit.SECONDS.sleep(2);} catch(Exception e){e.printStackTrace();}
        System.out.println(Thread.currentThread().getName()+" x="+x+"y="+y);  //expect result x=16,y=256

    }
}
public class test3 {

    public static void main(String[] args) throws InterruptedException {
        ReSortDemo reSortDemo=new ReSortDemo();
        for (int i = 1; i <=20 ; i++) {
            for (int j = 1; j <=10 ; j++) {
                new Thread(() -> {
                    //reSortDemo.method1();
                    //reSortDemo.method2();
                    reSortDemo.mySort();
                },String.valueOf(i)+"-"+String.valueOf(j)).start();
            }
        }
    }
}
