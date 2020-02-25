package com.nio;

import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/25
 * Description: 缓冲区(Buffer)： 负责数据的存取，就是数组，用于存储不同数据类型的数据
 * 根据数据类型不同(除boolean)
                 ByteBuffer
                 CharBuffer
                 ShortBuffer
                 IntBuffer
                 LongBuffer
                 FloatBuffer
                 DoubleBuffer
 * 缓冲区存取数据的核心方法:
 * put():存入数据到缓冲区中
 * get():获取缓冲区中的数据
 * 缓冲区中的四大核心属性
 * private int mark = -1;       标记：表示记录当前position的位置，可以通过reset()恢复到mark的位置
 * private int position = 0;    位置：表示缓冲区正在操作的位置（position<= limit <= capacity）
 * private int limit;           界限：表示缓冲区可以操作数据的大小
 * private int capacity;        容量：表示缓冲区中最大存储数据的容量，一旦声明无法改变
 *
 *
 * 直接缓冲区和非直接缓冲区
 *      非直接：通过allocate() 分配缓冲区，是建立在JVM的内存中
 *      直接：通过allocateDirect()分配直接缓存区，建立在物理内存中，可以提高效率
 *
 */
public class BufferDemo {
    public static void main(String[] args) {
//        byteBufferTest();

//        test2();
    }

    // create
    private static void testAllocateDirect(){
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);

    }

    private static void test2() {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put("abcde".getBytes());
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));

        System.out.println(buf.position());
        buf.mark();
        buf.get(dst,2,2);
        System.out.println(new String(dst,2,2));
        System.out.println(buf.position());

        buf.reset();
        System.out.println(buf.position());

        if (buf.hasRemaining())
            System.out.println(buf.remaining());
    }

    private static void byteBufferTest() {
        String string = "abcdefg";
        // 分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("----allocate after---");
        /*
                初始状态：
                    position = 0
                    capacity = limit =1024
         */
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 存数据
        buf.put(string.getBytes());
        /*
            写数据的模式
                position = 7
                capacity = limit = 1024
         */
        System.out.println("----put after----");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        // 切换成读模式
        buf.flip();
        /*
            读模式下
                position = 0
                limit = 7
                capacity = 1024
         */
        System.out.println("------flip() after----");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        // 读数据
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
        /*
            读数据
                position =7
                limit =7
                capacity =1024
         */
        System.out.println("---get() after---");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 可重复读数据
        buf.rewind();
        System.out.println("----rewind()----");
        /*
            position = 0,   limit = 7,  capacity = 1024
         */
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        // 清空缓冲区，但是缓冲区中的数据依然存在，但是出于被“遗忘”状态
        buf.clear();
        /*
            position=0,limit=capacity=1024
         */
        System.out.println("---clear()----");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        System.out.println((char) buf.get());
    }
}
