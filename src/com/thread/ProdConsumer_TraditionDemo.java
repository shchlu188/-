package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/23
 * Description:
 * 高内聚，低耦合
 * 线程 操作 资源类
 * 判断 干活 通知
 * 严防多线程下的虚假唤醒
 */
// 线程 操作 资源类
// 判断 干活 通知
// 防止虚假唤醒
class ShareData {    // 资源类
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            // 判断
            while (num != 0) {
                // 等待
                condition.await();
            }
            // 干活
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            // 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
    public void decrement() throws Exception {
        lock.lock();
        try {
            // 判断
            while (num == 0) {
                // 等待
                condition.await();
            }
            // 干活
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            // 通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}

public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        for (int i = 1; i <=5 ; i++) {
            new Thread(() -> {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "AAA").start();
            new Thread(() -> {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "BBB").start();
        }
    }
}
