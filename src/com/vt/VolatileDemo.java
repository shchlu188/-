package com.vt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * @author : chenglu
 * Date: 2020/2/21
 * Description:
 *
 *
 * CAS--->Unsafe--->CAS底层思想
 */
class MyData{
    volatile int num=0;
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addTo60(){
        this.num = 60;
    }
    public void addPlusPlus(){
        num++;
    }

    public void addAtomic(){
        atomicInteger.getAndDecrement();
    }
}
public class VolatileDemo {

    public static void main(String[] args){

    }

    private static void volatileNoAtomic() {
        MyData myData = new MyData();
        for(int i = 1; i <= 20; i++) {
                    new Thread(() -> {
                        for (int j = 0; j < 100; j++) {
                            myData.addPlusPlus();
                        }
                    }, String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"finally num is :" + myData.num);
    }

    private static void volatileDemo() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t com in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t finished:"+myData.num);
        }, "AAA").start();

        while (myData.num == 0){

        }
        System.out.println(Thread.currentThread().getName()+"\t execute finished");
    }
}
