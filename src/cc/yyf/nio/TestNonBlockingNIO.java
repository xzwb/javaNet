package cc.yyf.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 服务端
 */
public class TestNonBlockingNIO {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.bind(new InetSocketAddress(9999));

        // 获取选择器
        Selector selector = Selector.open();
        // 将通道注册到选择器,并且指定监听事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 轮询式的获取选择器上的已经就绪的准备就绪的事件
        while (selector.select() > 0) {
            // 获取当前选择器中的所有注册的选择键
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                // 获取准备就绪的事件
                SelectionKey selectionKey = it.next();
                // 判断具体是什么时间准备就绪
                if (selectionKey.isAcceptable()) {
                    // 获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 切换非阻塞
                    socketChannel.configureBlocking(false);
                    // 将通道注册到选择器
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if(selectionKey.isReadable()) {
                    // 获取读就绪通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                }
                // 取消选择键
                it.remove();
            }
        }
    }
}
