package com.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/23
 * Description: Semaphore
 * <p>  构造方法
 *     Semaphore​(int permits) 使用给定数量的许可和非公平公平设置创建 Semaphore 。
 *     Semaphore​(int permits, boolean fair) 使用给定数量的许可和给定的公平设置创建 Semaphore
 * </p>
 * <p>  获得许可证
 *      void acquire() 从此信号量获取许可，阻止直到一个可用，或者线程为 interrupted
 *      void acquire​(int permits) 从此信号量获取给定数量的许可，阻塞直到所有可用，或者线程为 interrupted
 * </p>
 * <p>  释放许可证
 *      void release() 发布许可证，将其返回到信号量
 *      void release​(int permits) 释放给定数量的许可，将它们返回到信号量
 * </p>
 * case: 抢车位
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    // 停2秒
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "\t 离开车位");
                }
            }, String.valueOf(i)).start();
        }
    }
}
