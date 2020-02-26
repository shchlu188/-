package com.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/25
 * Description: 通道(Channel):
 * 用于源节点和目标节点的连接，在java NIO中，负责缓冲区中数据的传输。本身不存储数据，需要配合缓冲区进行传输
 * 主要实现类：Interface java.nio.channels.Channel
 * FileChannel 用于读取，写入，映射和操作文件的通道。
 * SocketChannel 用于面向流的连接套接字的可选通道。
 * DatagramChannel 面向数据报的套接字的可选通道。
 * ServerSocketChannel 面向流的侦听套接字的可选通道。
 * <p>
 * 获取通道的方法：
 * getChannel()
 * 本地IO
 * FileInputStream/FileOutputStream
 * RandomAccessFile
 * 网络IO
 * Socket
 * ServerSocket
 * DatagramSocket
 * 在jdk1.7中的NIO.2针对各个通道提供了静态方法open()
 * 在jdk1.7中的NIO.2的Files工具类的newByteChannel()
 * <p>
 * 通道之间传输
 * transferFrom()
 * transferTo()
 * 分散(Scatter)和聚集(Gather)
 * 分散读取(Scattering Reads)  将通道中的数据分散到多个缓冲区
 * 聚集写入(Gathering Writes)  将多个缓冲区的数据聚集到通道中
 */
public class ChannelDemo {
    public static void main(String[] args) throws CharacterCodingException {
//        test5();

        Charset cs1 = Charset.forName("GBK");
        // 获取编码器
        CharsetEncoder encoder = cs1.newEncoder();
        // 获取解码器
        CharsetDecoder decoder = cs1.newDecoder();

        CharBuffer charBuffer = CharBuffer.allocate(1024);

        charBuffer.put("我是字符集编码");
        charBuffer.flip();

        // 编码
        ByteBuffer encodeBuffer = encoder.encode(charBuffer);

        System.out.println(encodeBuffer.limit());

        for (int i = 0; i < encodeBuffer.limit(); i++) {
            System.out.println(encodeBuffer.get());
        }
        // 解码
        System.out.println("----decoder---");
        encodeBuffer.flip();
        CharBuffer decodeBuffer = decoder.decode(encodeBuffer);
        System.out.println(decodeBuffer.toString());

    }

    /*
        字符集
            编码：字符串-->字节数组
            节码： 字节数组-->字符串

     */
    public static void test5() {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = map.entrySet();
        for (Map.Entry<String, Charset> entry : entries) {
            System.out.println(entry.getKey() + "===" + entry.getValue());
        }
    }

    public static void test4(String in, String out) throws IOException {
        RandomAccessFile rafIn = new RandomAccessFile(in, "rw");
        FileChannel inChannel = rafIn.getChannel();

        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        // 分散读取
        ByteBuffer[] buffers = {buf1, buf2};
        inChannel.read(buffers);
        for (ByteBuffer buffer : buffers) {
            buffer.flip();

        }
        System.out.println(new String(buffers[0].array(), 0, buffers[0].limit()));
        System.out.println("----------------------------");
        System.out.println(new String(buffers[1].array(), 0, buffers[1].limit()));


        // 聚集写入
        RandomAccessFile raf2 = new RandomAccessFile(out, "rw");
        FileChannel outChannel = raf2.getChannel();

        outChannel.write(buffers);


        outChannel.close();
        inChannel.close();
    }

    public static void test3(String in, String out) throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get(in), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(out), StandardOpenOption.WRITE, StandardOpenOption.READ);

        inChannel.transferTo(0, inChannel.size(), outChannel);

        inChannel.close();
        outChannel.close();


    }

    // 使用直接缓冲区完成文件的复制（内存映射文件）
    private static void test2(String inFileName, String outFileName) throws IOException {
        FileChannel inChannel = FileChannel.open(
                Paths.get(inFileName),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(
                Paths.get(outFileName),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW, StandardOpenOption.READ);
        // 内存映射文件
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapperBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMapperBuf.put(dst);

        inChannel.close();
        outChannel.close();

    }

    // 利用通道完成文件的复制
    private static void test1(String inFileName, String outFileName) throws IOException {
        FileInputStream fis = new FileInputStream(inFileName);
        FileOutputStream fos = new FileOutputStream(outFileName);

        // 获取通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        // 缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 将通道中的数据读入缓冲区
        while (inChannel.read(buf) != -1) {
            buf.flip(); // 切换成读模式
            outChannel.write(buf);
            buf.clear(); // 清空缓冲区
        }

        // 关闭资源
        outChannel.close();
        inChannel.close();
        fos.close();
        fis.close();


    }

}
