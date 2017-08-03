package com.hyht.LateLetter.util;

public class Util {
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
