package com.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/24
 * Description: java引用
 *      强引用：
 *
 *      软引用：由垃圾收集器根据内存需求自行决定清除。 软引用通常用于实现对内存敏感的缓存。
 *
 *      弱引用：只要垃圾回收机制运行就会被回收
 *
 *      虚引用：
 */

class SoftReferenceDemo{
    /*
        java.lang.Object@769c9116
        soft obj	java.lang.Object@769c9116
        ------gc after----
        null
        java.lang.Object@769c9116
     */
    public static void softRef_Memory_Enough(){
        Object obj = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj);
        System.out.println(obj);
        System.out.println("soft obj"+"\t"+softReference.get());
        obj=null;
        System.gc();
        System.out.println("------gc after----");
        System.out.println(obj);
        System.out.println(softReference.get());
    }
    public static void softRef_Memory_NotEnough(){
        Object obj = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj);
        System.out.println(obj);
        System.out.println("soft obj"+"\t"+softReference.get());
        obj=null;
        System.out.println("------gc after----");
        try {
            // 配置-Xms5m -Xmx5m -Xlog:gc*
            byte[] bytes = new byte[30*1024*1023];//产生大对象，使其出现OOM
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(obj);
            System.out.println(softReference.get());
        }
    }
}
public class ReferenceDemo {
    public static void main(String[] args){
    }
    // 弱引用
    private static void weakRef() {
        Object obj = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj);
        System.out.println(obj);
        System.out.println(weakReference.get());
        obj = null;
        System.gc();
        System.out.println("-------------");
        System.out.println(obj);
        System.out.println(weakReference.get());
    }

    // 强引用
    private static void strongRef() {
        Object obj1 = new Object();
        Object obj2 = obj1;
        obj1 = null;
        System.gc();
        System.out.println(obj2);
    }
}
