package com.oom;


import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/25
 * Description: -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *              ByteBuffer.allocateDirect()
 *              ByteBuffer.allocate()
 *
 *           异常：
 *              java.lang.OutOfMemoryError: Direct buffer memory
 *
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6*1024*1024);
    }
}
