package com.liupeng;

/**
 * Package: com.liupeng
 * Description：
 * Author: liupissc@cn.ibm.com
 * Date:  12/27/2019 4:27 PM
 * Modified By:
 */
import java.util.concurrent.*;
class MyThread implements  Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("************callable come in********");
        return 1024;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws  Exception{
        FutureTask<Integer> futureTask=new FutureTask<>(new MyThread());

        Thread t1=new Thread(futureTask,"AA");
        t1.start();

        while(!futureTask.isDone()){

        }
        int result=futureTask.get();  //建议放在最后，要求得到callable的计算结果，如果计算没有完成，会造成堵塞
        System.out.println(" thread return value is "+result);
    }
}
