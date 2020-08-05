package cc.yyf.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 分散(Scatter)与聚集(Gather)
 * 分散读取(Scattering Reads): 将通道中的数据分散到多个缓冲区中
 * 聚集写入(Gathering Writes): 将多个buffer中的数据聚集写入通道中
 */
public class ChannelTest2 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("/home/xzwb/2.txt", "rw");

        // 获取通道
        FileChannel channel = randomAccessFile.getChannel();

        // 分配多个缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        ByteBuffer buffer3 = ByteBuffer.allocate(2048);
        ByteBuffer[] buffers = {buffer1, buffer2, buffer3};
        channel.read(buffers);

        for (ByteBuffer byteBuffer : buffers) {
            byteBuffer.flip();
        }

        System.out.println(new String(buffers[0].array(), 0, buffers[0].limit()));
        System.out.println("------------------");
        System.out.println(new String(buffers[1].array(), 0, buffers[1].limit()));
        System.out.println("------------------");
        System.out.println(new String(buffers[2].array(), 0, buffers[2].limit()));

        // 聚集写入
        RandomAccessFile randomAccessFile1 = new RandomAccessFile("1.txt", "rw");
        FileChannel channel1 = randomAccessFile1.getChannel();

        channel1.write(buffers);
    }
}
