package com.nio;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/25
 * Description: 通道(Channel):
 *          用于源节点和目标节点的连接，在java NIO中，负责缓冲区中数据的传输。本身不存储数据，需要配合缓冲区进行传输
 *主要实现类：Interface java.nio.channels.Channel
 *  FileChannel 用于读取，写入，映射和操作文件的通道。
 *  SocketChannel 用于面向流的连接套接字的可选通道。
 *  DatagramChannel 面向数据报的套接字的可选通道。
 *  ServerSocketChannel 面向流的侦听套接字的可选通道。
 *
 * 获取通道的方法：
      getChannel()
           本地IO
               FileInputStream/FileOutputStream
               RandomAccessFile
           网络IO
               Socket
               ServerSocket
               DatagramSocket
 在jdk1.7中的NIO.2针对各个通道提供了静态方法open()
 在jdk1.7中的NIO.2的Files工具类的newByteChannel()



 *
 */
public class ChannelDemo {

}
