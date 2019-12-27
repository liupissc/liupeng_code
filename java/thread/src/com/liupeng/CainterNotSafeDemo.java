package com.liupeng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Package: com.liupeng
 * Description：集合类不安全的问题以及解决方法/读写分离的思想
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 2:33 PM
 * Modified By:
 */
import java.util.concurrent.*;
public class CainterNotSafeDemo {

    public static void main(String[] args) {
        //List<String> list=new ArrayList<>();
        //List<String> list= Collections.synchronizedList(new ArrayList<>());
        List<String> list=new CopyOnWriteArrayList<>();
        for (int i = 1; i <=3 ; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                //try{TimeUnit.SECONDS.sleep(3);} catch(Exception e){e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+" "+list);
            },String.valueOf(i)).start();
        }
    }
}
