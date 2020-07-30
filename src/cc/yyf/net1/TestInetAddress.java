package cc.yyf.net1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 测试ip
 */
public class TestInetAddress {
    public static void main(String[] args) {
        try {
            // 查询本机地址
            InetAddress byName = InetAddress.getByName("127.0.0.1");
            System.out.println(byName);
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println(inetAddress);
            // 查询百度地址
            InetAddress address = InetAddress.getByName("www.baidu.com");
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
