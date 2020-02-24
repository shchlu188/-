package com.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/23
 * Description:     java.lang.Thread
 *      Runnable  Callable
 *
    java.lang Interface Runnable
    java.util.concurrent Interface RunnableFuture<V> extends Runnable, Future<V>
    java.util.concurrent Class FutureTask<V> extends Object implements RunnableFuture<V>
        FutureTask​(Runnable runnable, V result) 创建一个 FutureTask,在运行时执行给定的Runnable
                                                并安排 get在成功完成时返回给定的结果。
        FutureTask​(Callable<V> callable) 创建一个 FutureTask ，在运行时将执行给定的 Callable 。


 */
class MyThread1 implements Runnable {

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("callable...........");
        return 1024;
    }
}
//boolean isDone() 如果此任务完成，则返回true 。 完成可能是由于正常终止，
//                  例外或取消 - 在所有这些情况下，此方法将返回true 。
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
