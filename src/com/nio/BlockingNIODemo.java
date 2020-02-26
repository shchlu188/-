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
 */
public class BlockingNIODemo {
    @Test
    public void client() throws IOException {
        SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
        FileChannel inChannel = FileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int len = 0;
        while (inChannel.read(buffer) != -1) {
            buffer.flip();
            clientChannel.write(buffer);
            buffer.clear();
        }
        clientChannel.shutdownOutput();
        // 接收服务端响应
        while ((len=clientChannel.read(buffer))!=-1){
            buffer.flip();
            System.out.println(new String(buffer.array(),0,len));
            buffer.clear();
        }

        inChannel.close();
        clientChannel.close();

    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();

        FileChannel outChannel = FileChannel.open(Paths.get("2.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        server.bind(new InetSocketAddress(8888));

        SocketChannel clientChannel = server.accept();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (clientChannel.read(buf)!=-1){
            buf.flip();
            outChannel.write(buf);
            buf.clear();
        }

        buf.put("服务响应消息".getBytes());
        buf.flip();
        clientChannel.write(buf);

        clientChannel.close();
        outChannel.close();
        server.close();
    }
}
