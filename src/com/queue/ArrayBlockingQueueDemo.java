package com.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/23
 * Description: ArrayBlockingQueue
 *      一：
 *        插入  boolean add​(E e) 在插入此队列的尾部，如果队列满了，抛出 IllegalStateException
 *        移除  E remove() 检索并删除此队列的头部,如果此队列为空，则抛出异常 NoSuchElementException
 *        检查  E element() 检索但不删除此队列的头部。如果此队列为空，则抛出异常 NoSuchElementException
 *      二：
 *        插入  boolean offer​(E e) 插入指定的元素在这个队列的尾部返回 true在成功和 false如果此队列已满。
 *        移除  E poll() 检索并删除此队列的头部，如果此队列为空，则返回 null
 *        检查  E peek() 检索但不移除此队列的头部，如果此队列为空，则返回 null
 *      三：
 *        插入  void put​(E e) 将指定元素插入此队列的尾部，等待队列已满时空间可用。
 *        移除  E take() throws InterruptedException 检索并删除此队列的头部，必要时等待，直到元素可用。
 *        检查
 *      四：
 *        插入  boolean offer​(E e, long timeout, TimeUnit unit) 将指定的元素插入此队列的尾部，
 *                                                  等待指定的等待时间，以便在队列已满时空间可用。
 *        移除  E poll​(long timeout, TimeUnit unit) throws InterruptedException
 *                                              检索并删除此队列的头部，如果元素可用，则等待指定的等待时间
 *        检查
 *     LinkedBlockingQueue
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        blockingQueue.put("d");

    }
}
