package cc.yyf.nio;

import java.nio.ByteBuffer;

/**
 * buffer:在javaNio中负责从数据的存取,缓冲区就是数组,用于存储不同数据类型的数据
 *
 * 根据数据类型不同,提供了对应类型的缓冲区（除了boolean）
 * byteBuffer
 * charBuffer
 * shortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方法几乎一致， 通过allocate()获取缓冲区
 *
 * 二、缓冲区存取方法的两个核心方法
 * put(): 存入数据到缓冲区
 * get(): 获取缓冲区中的数据
 *
 * 四、缓冲区中的四个核心属性
 * capacity: 容量,表示缓冲区中最大存储数据的容量,一旦声明不能改变
 * limit: 界限,表示缓冲区中的可以操作数据的大小，limit后的数据不能进行读写
 * position: 缓冲区中正在操作数据的位置
 * mark: 可以记录当前position的位置,可以通过reset()恢复到mark的位置
 *
 * position <= limit <= capacity
 */
public class BufferTest {
    public static void main(String[] args) {
        // 分配一个指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("------------------------------------");
        System.out.println("position = " + buffer.position());
        System.out.println("limit = " + buffer.limit());
        System.out.println("capacity = " + buffer.capacity());

        // 2.利用put方法存入数据到缓冲区中
        String message = "hello,world";
        buffer.put(message.getBytes());
        System.out.println("------------------------------------");
        System.out.println("position = " + buffer.position());
        System.out.println("limit = " + buffer.limit());
        System.out.println("capacity = " + buffer.capacity());

        // 3.进入读数据模式
        buffer.flip();
        System.out.println("------------------------------------");
        System.out.println("position = " + buffer.position());
        System.out.println("limit = " + buffer.limit());
        System.out.println("capacity = " + buffer.capacity());

        // 4.读入字节数组
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.out.println("------------------------------------");
        System.out.println("position = " + buffer.position());
        System.out.println("limit = " + buffer.limit());
        System.out.println("capacity = " + buffer.capacity());
        System.out.println("byte[] = " + bytes);

        // 5.rewind: 可重复读数据;
        buffer.rewind();
        System.out.println("------------------------------------");
        System.out.println("position = " + buffer.position());
        System.out.println("limit = " + buffer.limit());
        System.out.println("capacity = " + buffer.capacity());

        // 6.清空缓冲区,但是缓冲区中的数据依然存在,处于被遗忘状态
        buffer.clear();
        System.out.println("------------------------------------");
        System.out.println("position = " + buffer.position());
        System.out.println("limit = " + buffer.limit());
        System.out.println("capacity = " + buffer.capacity());

    }
}
