package cc.yyf.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 利用直接缓冲区完成文件的复制(内存映射文件)
 */
public class ChannelTest {
    public static void main(String[] args) throws IOException {
        /**
         * create_New: 存在就创建,不存在就报错
         * create: 不存在就创建,存在就覆盖
         */
        FileChannel inChannel = FileChannel.open(Paths.get("/home/xzwb/1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

        System.out.println(inChannel.size());
        // 内存映射文件, 现在的缓冲区就在物理内存中
        MappedByteBuffer inMapBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        System.out.println(inMapBuffer.limit());
        // 直接对缓冲区进行数据的读写
        byte[] buffer = new byte[inMapBuffer.limit()];
        inMapBuffer.get(buffer);
        outMapBuffer.put(buffer);

        inChannel.close();
        outChannel.close();


    }
}
