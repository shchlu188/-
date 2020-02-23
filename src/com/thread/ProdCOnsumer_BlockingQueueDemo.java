package com.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/23
 * Description:
 */
class MySource {
    private volatile boolean FLAG = true; // 默认开启，进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger(1);
    private BlockingQueue<String> blockingQueue = null;

    // 构造注入
    public MySource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println("blockingQueue name is:" + blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        while (FLAG) {
            data = atomicInteger.getAndIncrement() + "";
            boolean retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t insert Queue-->" + data + " success");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t insert Queue-->" + data + " failed");
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t stop insert, FLAG=false, produce finished");
    }

    public void myConsumer() throws Exception {
        String result;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t over 2 seconds, exit myConsumer() method");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t get Queue value-->" + result + " success");
        }
    }

    public void stop(){
        FLAG = false;
    }
}

public class ProdCOnsumer_BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
      MySource mySource = new MySource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            try {
                mySource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();
        new Thread(() -> {
            try {
                mySource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Cons").start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t main thread stop");
        mySource.stop();
    }

}
