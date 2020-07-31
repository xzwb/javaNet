package cc.yyf.udp;

import java.io.IOException;
import java.net.*;

/**
 * 不需要连接服务器
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 建立一个socket
        DatagramSocket socket = new DatagramSocket();

        // 发送给谁
        InetAddress name = InetAddress.getByName("localhost");
        int port = 9090;
        // 建立一个包
        String msg = "hello,world";
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), 0, msg.getBytes().length, name, port);
        socket.send(packet);
        socket.close();
    }
}
