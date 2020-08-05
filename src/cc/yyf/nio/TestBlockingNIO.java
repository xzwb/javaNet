package cc.yyf.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、使用NIO完成网络通信的三个核心点
 *
 * 1.通道(Channel): 负责连接
 *      SocketChannel TCP
 *      ServerSocketChannel TCP
 *      DatagramChannel UDP
 * 2.缓冲区(Buffer): 负责数据的存取
 *
 * 3.选择器(Selector): 是SelectableChannel的多路复用器,用于监控SelectableChannel的IO状况;
 */
public class TestBlockingNIO {
    public static void main(String[] args) throws IOException {
        // 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

        // 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9999));

        // 获取客户端连接的通道
        SocketChannel accept = serverSocketChannel.accept();

        // 分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 接受客户端的数据保存到本地
        while (accept.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        accept.close();
        outChannel.close();
        serverSocketChannel.close();
    }
}
