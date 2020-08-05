package cc.yyf.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * 客户端
 */
public class ClientNonBlocking {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
        // 切换非阻塞模式
        socketChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(new Date().toString().getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();

        socketChannel.close();
    }
}
