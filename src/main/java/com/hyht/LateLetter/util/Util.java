package com.hyht.LateLetter.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Util {

    //直接定义服务器IP
    private static final String SERVER_IP = "http://183.3.221.181";

    public static String getServerIp() {
        return SERVER_IP;
    }

    // 提取本地的IP地址，作为图片的url发送给前端，而数据库只存储相对路径（测试用）
    public static String getLocalIP() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }
        // System.out.println(ipAddrStr);
        return ipAddrStr;
    }

    public static String imgSuffix(String srcPrix) throws Exception {
        String suffix = "";
        if ("data:image/jpeg;".equalsIgnoreCase(srcPrix)) {// data:image/jpeg;base64,base64编码的jpeg图片数据
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(srcPrix)) {// data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(srcPrix)) {// data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(srcPrix)) {// data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        } else {
            throw new Exception("上传图片格式不合法");
        }
        return suffix;
    }
}
