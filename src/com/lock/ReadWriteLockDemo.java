package com.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache<K, V> {
    Map<K, V> map = new HashMap<>();
    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void put(K key, V value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t add key-->" + key);
            TimeUnit.SECONDS.sleep(1);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t add finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }

    }

    public V get(K key) {
        rwLock.readLock().lock();
        V result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "\t read key-->" + key);
            TimeUnit.SECONDS.sleep(1);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t read finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }

        return result;
    }
}

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/23
 * Description:
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache<String, String> myCache = new MyCache<>();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.get(finalI + "");
            }, String.valueOf(i)).start();
        }
    }
}
