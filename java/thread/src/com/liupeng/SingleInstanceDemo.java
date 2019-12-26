package com.liupeng;

/**
 * Package: com.liupeng
 * Description：单例模式在多线程下的问题及解决
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 12:54 PM
 * Modified By:
 */
public class SingleInstanceDemo {
    private static SingleInstanceDemo instance=null;

    private SingleInstanceDemo(){
        System.out.println(Thread.currentThread().getName()+" 我是构造方法");
    }

    public static SingleInstanceDemo getInstance(){
        if (instance==null){
            instance=new SingleInstanceDemo();
        }
        return instance;
    }

    public static void main(String[] args) {
//        System.out.println(SingleInstanceDemo.getInstance()==SingleInstanceDemo.getInstance());
//        System.out.println(SingleInstanceDemo.getInstance()==SingleInstanceDemo.getInstance());
//        System.out.println(SingleInstanceDemo.getInstance()==SingleInstanceDemo.getInstance());
//
//        System.out.println();
//        System.out.println();
//        System.out.println();

        for (int i = 1; i <=100000 ; i++) {
            new Thread(() -> {
                SingleInstanceDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
