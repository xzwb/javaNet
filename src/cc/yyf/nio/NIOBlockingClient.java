package cc.yyf.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 客户端
 */
public class NIOBlockingClient {
    public static void main(String[] args) throws IOException {
        // 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));

        // 分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取本地文件并发送到服务端
        FileChannel fileChannel = FileChannel.open(Paths.get("/home/xzwb/1.jpg"), StandardOpenOption.READ);

        while (fileChannel.read(buffer) != -1) {
            buffer.flip();
            sChannel.write(buffer);
            buffer.clear();
        }

        fileChannel.close();
        sChannel.close();
    }
}
