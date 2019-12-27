package com.liupeng;

/**
 * Package: com.liupeng
 * Description：
 * Author: liupissc@cn.ibm.com
 * Date:  12/27/2019 3:20 PM
 * Modified By:
 */
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean flag=true; //默认开启，进行生产消费
    private AtomicInteger atomicInteger=new AtomicInteger();

    BlockingQueue<String> blockingQueue=null;

    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
        System.out.println(this.blockingQueue.getClass().getName());
    }

    public void myProduce() throws  Exception{
        String data=null;
        boolean retValue;
        while(flag){
            data=atomicInteger.incrementAndGet()+"";
            retValue=blockingQueue.offer(data,2,TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+" 插入队列成功");
            }else{
                System.out.println(Thread.currentThread().getName()+" 插入队列失败");
            }
            try{TimeUnit.SECONDS.sleep(1);} catch(Exception e){e.printStackTrace();}
        }
        System.out.println(Thread.currentThread().getName()+" 老板叫停，表示flag=false,生产动作结束");
    }

    public void myConsume() throws  Exception{

        String result=null;
        while(flag){
            result=blockingQueue.poll(2,TimeUnit.SECONDS);

            if (null==result||result.equalsIgnoreCase("")){
                flag=false;
                System.out.println(Thread.currentThread().getName()+" 超过2秒钟没有取到，消费结束");
                System.out.println();
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+" 消费队列 "+result+" 成功");
            try{TimeUnit.SECONDS.sleep(1);} catch(Exception e){e.printStackTrace();}
        }
    }
    public void stop(){
        this.flag=false;
    }
}
public class ProductConsumer_BlockingQueueDemo {
    public static void main(String[] args) {
        MyResource myResource=new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+" 生产线程启动");
            try {
                myResource.myProduce();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"produce").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+" 消费线程启动");
            try {
                myResource.myConsume();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"consume").start();

        try{TimeUnit.SECONDS.sleep(5);} catch(Exception e){e.printStackTrace();}
        myResource.stop();

        System.out.println(Thread.currentThread().getName()+ " 5秒钟时间到，生产消费结束");
    }
}
