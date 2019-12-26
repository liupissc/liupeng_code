package com.liupeng;

/**
 * Package: com.liupeng
 * Description：读写锁代码演示
 * 写操作必须要是原子操作，中间不能被打断
 * * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 5:13 PM
 * Modified By:
 */
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,Object> map=new HashMap<>();
    private ReentrantReadWriteLock rwlock=new ReentrantReadWriteLock();

    public void putWithLock(String key,Object value){
        rwlock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+" 正在写入"+key);
            try{TimeUnit.MILLISECONDS.sleep(300);} catch(Exception e){e.printStackTrace();}
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+" 写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwlock.writeLock().unlock();
        }


    }

    public void put(String key,Object value){

        System.out.println(Thread.currentThread().getName()+" 正在写入"+key);
        try{TimeUnit.MILLISECONDS.sleep(300);} catch(Exception e){e.printStackTrace();}
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+" 写入完成");

    }
    public void getWithLock(String key){

        rwlock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+" 正在读"+key);
            try{TimeUnit.MILLISECONDS.sleep(300);} catch(Exception e){e.printStackTrace();}
            Object result=map.get(key);
            System.out.println(Thread.currentThread().getName()+" 读完成 "+result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwlock.readLock().unlock();
        }
    }
    public void get(String key){

        System.out.println(Thread.currentThread().getName()+" 正在读"+key);
        try{TimeUnit.MILLISECONDS.sleep(300);} catch(Exception e){e.printStackTrace();}
        Object result=map.get(key);
        System.out.println(Thread.currentThread().getName()+" 读完成 "+result);

    }

    public void clearMap(){
        map.clear();
    }

}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache=new MyCache();

        for (int i = 1; i <=5 ; i++) {
            final int tempInt=i;
            new Thread(() -> {
                //myCache.put(tempInt+"",tempInt+"");
                myCache.putWithLock(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=5 ; i++) {
            final int tempInt=i;
            new Thread(() -> {
                //myCache.get(tempInt+"");
                myCache.getWithLock(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
