package com.liupeng;

/**
 * Package: com.liupeng
 * Description：
 * Author: liupissc@cn.ibm.com
 * Date:  12/26/2019 10:55 PM
 * Modified By:
 */
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.*;
public class BlockingQueueDemo {
    public static void main(String[] args) throws  Exception {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        //part 1 exception group
        /*
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.add("x"));

        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());
        */

        //part 2 boolean group
        /*
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        */

        //part 3 block group
//        blockingQueue.put("a");
//        blockingQueue.put("b");
//        blockingQueue.put("c");
//        System.out.println("*********************************************");
//        //blockingQueue.put("x");
//
//        blockingQueue.take();
//        blockingQueue.take();
//        blockingQueue.take();
//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        blockingQueue.take();
//        System.out.println("finish!");

        //part 4 timeout group
        System.out.println(blockingQueue.offer("a",2,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",2,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",2,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",2,TimeUnit.SECONDS));
    }

}
