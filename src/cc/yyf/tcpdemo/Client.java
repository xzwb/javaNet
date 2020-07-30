package cc.yyf.tcpdemo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * tcp实现发送消息,客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 服务端地址
        InetAddress address = InetAddress.getByName("127.0.0.1");
        // 端口号
        int port = 9999;
        // 创建一个socket连接
        Socket socket = new Socket(address, port);
        // 发送消息 IO流
        OutputStream os = socket.getOutputStream();
        os.write("hello world".getBytes());
        os.close();
        socket.close();

    }
}
