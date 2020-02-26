package com.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/27
 * Description:
 *
 *  使用NIO的核心：
 *      channel  负责连接
 *
 *          Pipe.SinkChannel 表示Pipe的可写端的通道 。
 *          Pipe.SourceChannel 表示Pipe可读端的通道
 *
 *          DatagramChannel 面向数据报的套接字的可选通道。
 *          ServerSocketChannel 面向流的侦听套接字的可选通道。
 *          SocketChannel 用于面向流的连接套接字的可选通道。
 *      buffer   负责数据的存储
 *      selector
 */
public class BlockingIODemo {
    @Test
    public void client() throws IOException {
        // 获取通道
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        FileChannel inchannel = FileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);
        // 分配指定大小的 缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (inchannel.read(buf)!=-1){
            buf.flip();
            channel.write(buf);
            buf.clear();
        }

        inchannel.close();
        channel.close();
    }

    @Test
    public void server() throws IOException {
        // 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        FileChannel outChannel = FileChannel.open(Paths.get("2.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
        // 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9898));

        SocketChannel clientChannel = serverSocketChannel.accept();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (clientChannel.read(buffer)!=-1){
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        // close

        clientChannel.close();
        outChannel.close();
        serverSocketChannel.close();


    }
}
