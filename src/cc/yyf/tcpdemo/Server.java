package cc.yyf.tcpdemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * tcp发送消息,服务端
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 开启一个地址
        ServerSocket socket = new ServerSocket(9999);
        // 等待客户端连接过来
        Socket accept = socket.accept();
        // 读取客户端的消息
        InputStream is = accept.getInputStream();
        // 管道流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }

        System.out.println(byteArrayOutputStream.toString());
        byteArrayOutputStream.close();
        is.close();
        accept.close();
        socket.close();
    }
}
