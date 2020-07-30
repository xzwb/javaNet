package cc.yyf.tcpdemo02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * tcp 客户端发文件
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 创建连接
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
        // 创建一个输出流
        OutputStream os = socket.getOutputStream();
        // 读取文件
        FileInputStream file = new FileInputStream(new File("/home/xzwb/1.jpg"));
        // 写出文件
        byte[] buffer = new byte[1024];
        int len;
        while ((len = file.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }

        // 关闭资源
        file.close();
        os.close();
        socket.close();
    }
}
