package com.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/24
 * Description:
 * <p>
 * ThreadPoolExecutor(int corePoolSize, 线程核心数
                      int maximumPoolSize, 最大容纳并执行的线程数
                      long keepAliveTime, 空闲时存活时间
                      TimeUnit unit,   存活时间的单位
                      BlockingQueue<Runnable> workQueue, 任务队列，被提交但还没执行的任务
                      ThreadFactory threadFactory, 生产线程的工厂
                      RejectedExecutionHandler handler) 拒绝策略，表示任务队列满时。采用的策略
 * <p>
 *
 *
 * static ExecutorService newCachedThreadPool() 创建一个根据需要创建新线程的线程池，可以自动扩展线程数大小
 *          底层最大容纳线程是：Integer.MAX_VALUE,阻塞队列用的是new SynchronousQueue<Runnable>()
 * static ExecutorService newFixedThreadPool​(int nThreads) 创建一个线程池，该池重用在共享的无界队列中运行的固定数量的线程。
 *          底层队列用的是：new LinkedBlockingQueue<Runnable>()，而且此阻塞队列的大小为Integer.MAX_VALUE
 * static ExecutorService newSingleThreadExecutor() 创建一个Executor，它使用一个在无界队列中运行的工作线程。
 *          底层队列用的是：new LinkedBlockingQueue<Runnable>()，而且此阻塞队列的大小为Integer.MAX_VALUE
 *
 *
 *     cpu密集型：cpu核数+1
 *
 *     io密集型： cpu核数/（1-阻塞系数）  阻塞系数一般0.8~0.9
 *
 *
 *
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool(); // 一池N个线程数
//        ExecutorService executorService = Executors.newFixedThreadPool(3); //  一池固定数线程数
        ExecutorService executorService = Executors.newSingleThreadExecutor(); // 一池一个线程数

        try {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
