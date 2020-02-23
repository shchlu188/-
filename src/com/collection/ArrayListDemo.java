package com.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/22
 * Description:
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();//Collections.synchronizedMap(new HashMap<>());//new HashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void SetNotSafe() {
        Set<String> set =new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>()); //new HashSet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        /**
         *  底层数组(Object类型，初始值为10)
         *  线程不安全
         *  抛异常：java.util.ConcurrentModificationException
         *
         *
         *      故障现象
         *          java.util.ConcurrentModificationException
         *      导致原因
         *
         *      解决方案
         *          new Vector();
         *          Collections.synchronizedList(new ArrayList<>());
         *          new CopyOnWriteArrayList<>();
         *
         *      优化建议
         */
//        List<String> list = Arrays.asList("a", "b", "c");
//        list.stream().forEach(System.out::println);
//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
