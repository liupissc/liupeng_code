package com.liupeng;

/**
 * Package: com.liupeng
 * Description：
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 1:39 PM
 * Modified By:
 */
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,2019)+" current value is "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,1024)+" current value is "+atomicInteger.get());
    }
}
