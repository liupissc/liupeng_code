package com.liupeng;

/**
 * Package: com.liupeng
 * Description：倒计时
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 9:29 PM
 * Modified By:
 */
import java.util.concurrent.*;

import jdk.nashorn.internal.objects.annotations.Getter;

enum CountryEnum{
    ONE(1,"齐","齐王"),TWO(2,"楚","楚王"),THREE(3,"燕","燕王"),FOUR(4,"赵","赵王"),FIVE(5,"魏","魏王"),SIX(6,"韩","韩王");

    private  Integer retCode;
    private  String  retCountry;

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetCountry() {
        return retCountry;
    }

    public void setRetCountry(String retCountry) {
        this.retCountry = retCountry;
    }

    public String getRetKing() {
        return retKing;
    }

    public void setRetKing(String retKing) {
        this.retKing = retKing;
    }

    private  String  retKing;
    CountryEnum(Integer retCode,String retCountry,String retKing){
        this.retCode=retCode;
        this.retCountry=retCountry;
        this.retKing=retKing;
    }

    public static CountryEnum foreach(int index){
        CountryEnum[] countryEnums=CountryEnum.values();

        for (CountryEnum element : countryEnums){
            if (index==element.getRetCode()){
                return element;
            }
        }

        return  null;
    }
}

public class CountDownLatchDemo {
    public static void main(String[] args) throws  Exception {
        /*
        //part1 without CountDownLatch

        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+" 上完自习，离开教室");
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName()+" ************班长最后关门走人");
        */

        /*
        //part2 with CountDownLatch
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+" 上完自习，离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+" ************班长最后关门走人");
        */


        //part3 with CountDownLatch Plus Enum demo
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+" 国被灭");
                countDownLatch.countDown();
            },CountryEnum.foreach(i).getRetCountry()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+" ************秦统一中国");

    }
}
