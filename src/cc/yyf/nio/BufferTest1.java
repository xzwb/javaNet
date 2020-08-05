package cc.yyf.nio;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接缓冲区与非直接缓冲区
 *
 * 非直接: 通过allocate()方法分配缓冲区,将缓冲区建立在jvm的内存中
 * 直接: 通过allocateDirect()方法分配缓冲区,将缓冲区建立在操作系统的内存中;
 *
 * 通道(Channel): 用于源节点与目标节点的连接,在NIO中负责缓冲区中数据的传输,Channel本身不存储数据,因此需要配合缓冲区进行传输
 *
 * 二、通道的主要实现类
 * FileChannel
 * SocketChannel
 * ServerSocketChannel
 * DatagramChannel
 *
 * 三、获取通带
 * 1. Java针对支持通道的类提供了getChannel()方法
 * 2. 在jdk1.7中的NIO2提供了一个静态方法,open()
 * 3.在jdk1.7中的NIO2的Files的工具类的newByteChannel()
 */
public class BufferTest1 {

    public static void main(String[] args) throws IOException {
        // 创建直接缓冲区
        // ByteBuffer buffer = ByteBuffer.allocateDirect(1024);


        // 利用通道完成文件的复制
        FileInputStream fis = new FileInputStream("/home/xzwb/1.jpg");
        FileOutputStream fos = new FileOutputStream("2.jpg");

        // 获取通道
        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        // 分配指定缓冲区大小
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 将通道中的数据读到buffer中
        while (fisChannel.read(buffer) != -1) {
            // 切换成读取数据的模式
            buffer.flip();
            // 将缓冲区中的数据写入通道
            fosChannel.write(buffer);
            // 清空缓冲区
            buffer.clear();
        }

        // 关闭
        fosChannel.close();
        fisChannel.close();
        fis.close();
        fos.close();
    }
}
