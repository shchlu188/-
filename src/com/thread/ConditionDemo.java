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
 * synchronized 和 Lock有什么区别：
 * 1、原始结构
 * synchronized 是关键字，属于JVM层面
 * monitorenter(底层时通过monitor对象完成的,wait/notify等方法也依赖于monitor对象，
 * 只有在同步代码块和方法中才能调用)
 * monitorexit(出现两次，保证正常和异常都能退出)
 * Lock是具体类(java.util.concurrent.Locks.Lock) 是API层的锁
 * 2、使用方法：
 * synchronized 不需要用户自己去手动释放锁，执行代码完成时系统会自动让线程释放锁的占用
 * ReentrantLock 则需要用户自己去释放锁，若没有主动释放锁，会导致出现思索现象。
 * 3、等待是否可中断：
 * synchronized 不可中断，要么正常退出，要么抛出异常
 * ReentrantLock 可中断：1、设置超时方法tryLock(Long timeout,TimeUnit unit)
 * 2、lockInterruptibly()放代码块中，调用interrupt()方法可中断
 * 4、加锁是否公平：
 * synchronized 非公平锁
 * ReentrantLock 可以设置是否公平，默认为不公平
 * 5、锁绑定多个条件Condition
 * synchronized 没有
 * ReentrantLock 用来实现分组唤醒需要唤醒的线程们，可以精确唤醒线程，
 * 而不是向synchronized，要么随机唤醒一个，要么唤醒所有;
 */
/*
    多线程之间顺序调用，实现A->B->C三个线程启动，要求如下
    A 打印5次，B 打印10 C 打印15次
    来10轮
 */
class SharaData {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition_A = lock.newCondition(); // num =0
    private Condition condition_B = lock.newCondition(); // num =1
    private Condition condition_C = lock.newCondition(); // num =2
    private void printCount(int target,Condition cur,Condition next,int count){
        lock.lock();
        try {
            while (num %3 !=target){
                cur.await();
            }
            for (int i = 1; i <=count ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t "+i);
            }
            num++;
            next.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printA(int count){
        printCount(0, condition_A, condition_B, count);
    }
    public void printB(int count){
        printCount(1, condition_B, condition_C, count);
    }
    public void printC(int count){
        printCount(2, condition_C, condition_A, count);
    }
    public void print5() {
        lock.lock();
        try {
            while (num != 1) {
                condition_A.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 2;
            condition_B.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (num != 2) {
                condition_B.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 3;
            condition_C.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (num != 3) {
                condition_C.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 1;
            condition_A.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class ConditionDemo {
    public static void main(String[] args) {
        SharaData sharaData = new SharaData();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharaData.printA(5);
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharaData.printB(10);
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharaData.printC(15);
            }
        }, "C").start();
    }
}
