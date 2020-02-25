package com.oom;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/25
 * Description: -Xmx10m -Xms10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *      java.lang.OutOfMemoryError: GC overhead limit exceeded
 */
public class GCOverHeadDemo {
    public static void main(String[] args) {
        int i = 0;
        ArrayList<String> list = new ArrayList<>();

        try {

            while (true) {
                list.add(String.valueOf(i++).intern());
            }
        } catch (Throwable e) {
            System.out.println("***************"+i);
            e.printStackTrace();
            throw e;
        } finally {

        }

    }
}
