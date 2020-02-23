package com.thread;

import java.util.concurrent.CountDownLatch;

enum CountryEnum {
    ONE(1, "齐"),
    TWO(2, "楚"),
    three(3, "燕"),
    four(4, "赵"),
    five(5, "魏"),
    six(6, "韩"),;
    private int num;
    private String name;

    CountryEnum(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public static CountryEnum foreach_Country(int num) {
        for (CountryEnum countryEnum : CountryEnum.values()) {
            if (countryEnum.getNum() == num) {
                return countryEnum;
            }
        }
        return null;
    }
}

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/23
 * Description: CountDownLatch（相当于做加法）
 *  *让一些线程阻塞直到另一些线程完成一些列操作之后才被唤醒
 *  *主要由两个方法
 *      await(): 当一个线程或多个线程调用时，会被阻塞；
 *      countDown(): 会将计数器减一，
 *      当计数器减为0时，调用await()方法的线程会被唤醒，继续执行
 */
public class CountDownLatchDemo {
    public static final int DEFAULT_COUNT = 6;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(DEFAULT_COUNT);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国，被灭");
                countDownLatch.countDown();
            }, CountryEnum.foreach_Country(i).getName()).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t *******秦国统一天下");

    }
}
