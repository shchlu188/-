package com.pool;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/24
 * Description: 自定义线程池
 *  static class  ThreadPoolExecutor.AbortPolicy 拒绝任务的处理程序，抛出RejectedExecutionException 。
 *  static class  ThreadPoolExecutor.CallerRunsPolicy 被拒绝任务的处理程序，直接在 execute方法的调用线程中运行被拒绝的任务，除非执行程序已关闭，在这种情况下，任务将被丢弃。
 *  static class  ThreadPoolExecutor.DiscardOldestPolicy 拒绝任务的处理程序，丢弃最旧的未处理请求，然后重试 execute ，除非执行程序关闭，在这种情况下，任务将被丢弃。
 *  static class  ThreadPoolExecutor.DiscardPolicy 拒绝任务的处理程序，以静默方式丢弃被拒绝的任务。
 */
public class RejectDemo {
    public static void main(String[] args){
        System.out.println(Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                2,
                2,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
                );
        try {
            for (int i = 0; i <6 ; i++) {
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
