package com.oom;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/25
 * Description: java.lang.OutOfMemoryError: Java heap space
 *  配置 -Xmx10m -Xmx10m
 *
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args){
      String string = "asd";
      while (true){
          string+= string +new Random(11111)+new Random(22222);
          string.intern();
      }
    }
}
