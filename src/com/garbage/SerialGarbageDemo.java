package com.garbage;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/25
 * Description:
 *  java -XX:+PrintCommandLineFlags -version
 *
 *
 */
public class SerialGarbageDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello gc");
        String string = "asd";
        while (true){
            string+= string +new Random(11111)+new Random(22222);
            string.intern();
        }
    }
}
