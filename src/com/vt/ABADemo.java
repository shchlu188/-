package com.vt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/22
 * Description:
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        System.out.println("---------------ABA问题的产生---------------------");
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            System.out.println(Thread.currentThread().getName()+"\t current value:"+atomicReference.get());
            atomicReference.compareAndSet(101, 100);
            System.out.println(Thread.currentThread().getName()+"\t current value:"+atomicReference.get());
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet(100, 2020);
            System.out.println(Thread.currentThread().getName() + "\t current value:" + atomicReference.get());

        }, "t2").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------------ABA问题解决--------------");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t first version:" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(
                    100,
                    101,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t second version:" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(
                    101,
                    100,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t third version:" + atomicStampedReference.getStamp());
        }, "t3").start();
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t first version:" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(
                    100,
                    2020,
                    stamp,
                    stamp + 1);

            System.out.println(Thread.currentThread().getName() + "\t update success?" + result + "\t current version:" + atomicStampedReference.getStamp());
        }, "t4").start();
    }
}
