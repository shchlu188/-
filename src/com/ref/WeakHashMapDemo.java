package com.ref;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/25
 * Description:Class WeakHashMap<K,​V>  extends AbstractMap<K,​V> implements Map<K,​V>
 *          java.lang.Object
 *              java.util.AbstractMap<K,​V>
 *                   java.util.WeakHashMap<K,​V>
 *   基于哈希表的Map接口的实现，具有弱键 。 WeakHashMap的条目在其密钥不再正常使用时将自动删除。
 *   更确切地说，给定密钥的映射的存在不会阻止密钥被垃圾收集器丢弃，即，可以最终化，最终化，然后回收。
 *   当一个键被丢弃时，它的条目将被有效地从地图中删除，因此该类的行为与其他Map实现略有不同。
 */
public class WeakHashMapDemo {
    public static void main(String[] args){
      myHashMap();
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> hashMap = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "WeakHashMap";
        hashMap.put(key, value);
        System.out.println(hashMap);
        key = null;
        System.out.println("------key = null -----");
        System.out.println(hashMap);
        System.gc();
        System.out.println("------gc after--------");
        System.out.println(hashMap);
    }

    private static void myHashMap() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        Integer key = new Integer(1);
        String value = "hashMap";
        hashMap.put(key, value);
        System.out.println(hashMap);
        key = null;
        System.out.println("------key = null -----");
        System.out.println(hashMap);
        System.gc();
        System.out.println("------gc after--------");
        System.out.println(hashMap);


    }
}
