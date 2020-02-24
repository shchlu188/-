package com.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/24
 * Description: 死锁编码和定位分析
 * 死锁：指两个或两个以上的进程，因抢夺资源造成的一种互相等待的现象，若无外力干涉都见无法推进下去，
 * 如果资源充足，进程的资源请求都能得到满足，死锁出现的可能性就会很低，否则就会陷入死循环
 * <p>
 * 总结： 系统资源不足、进程循行推进的顺序不合适、资源的分配不当
 * <p>
 * 解决
 *      jps -l 定位
 *      jstack 进程号
 */
class HoldLock implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockA + "\t 尝试获得" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockB + "\t 尝试获得" + lockA);

            }
        }


    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLock(lockA, lockB),"AAA").start();
        new Thread(new HoldLock(lockB, lockA), "BBB").start();
    }
}
