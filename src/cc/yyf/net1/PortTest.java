package cc.yyf.net1;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * 端口测试
 */
public class PortTest {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080);
        System.out.println(inetSocketAddress);
        // 返回Inetaddress
        InetAddress address = inetSocketAddress.getAddress();

    }
}
