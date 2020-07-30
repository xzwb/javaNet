package cc.yyf.tcpdemo02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * tcp 服务端接收文件
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 创建服务
        ServerSocket socket = new ServerSocket(9999);
        // 监听客户端的连接
        Socket accept = socket.accept();
        // 获取输入流
        InputStream inputStream = accept.getInputStream();
        // 文件输出
        FileOutputStream fileOutputStream = new FileOutputStream(new File("/home/xzwb/receive.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, len);
        }

        fileOutputStream.close();
        inputStream.close();
        accept.close();
        socket.close();
    }
}
