package com.hyht.LateLetter;

public class EnvirArgs {
    //服务器地址
    public static String serverIp = "183.3.221.181";

    /**
     *  额外文件的存放地址，系统绝对路径
     */
    public static String extraFilePath = "d:\\nginx_file\\LateLetter";

    /**
     *  返回给前端的静态文件资源路径，用于nginx索引
     */
    public static String internetFileUrl = "http://"+ serverIp +":8848/LateLetter";



}
