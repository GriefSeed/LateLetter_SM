package com.hyht.LateLetter.web;


import com.hyht.LateLetter.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/BFH")
public class BigFileHandlerController {


    @Autowired
    ServletContext servletContext;

    @RequestMapping("/addPic")
    public List addPic(@RequestBody String[] picArray) throws Exception {
        //定义数组，存储资源路径，用于返回给前端，存入数据库
        List<String> filePathList = new ArrayList<String>();

        // 遍历数组，取其中的BASE64图片格式，转换为文件后存储
       /* for (String str : picArray) {
            System.out.println(str);
        }*/
        for (int i = 0; i < picArray.length; i++) {
            String imgName = ((new Date()).getTime() / 1000 + "") + i;
            String[] d = picArray[i].split("base64,");
            String suffix = Util.imgSuffix(d[0]);
            // 使用spring的base64工具包转二进制
            byte[] bs = Base64Utils.decodeFromString(d[1]);
            //File file = new File(System.getProperty("webapp.root") + "\\img\\" + imgName + suffix);
            //File file = new ClassPathResource("/picture/" + imgName + suffix).getFile();

            //上面的是使用Spring MVC配置时的用法，而这个servletContext通过注解引入默认配置，可以获取项目根路径
            File file = new File(servletContext.getRealPath("/pic/") + imgName + suffix);
            file.createNewFile();
            OutputStream os = new FileOutputStream(file);
            os.write(bs);
            os.flush();
            os.close();
            filePathList.add(Util.getServerIp() + ":8081/pic/" + imgName + suffix);
        }
        return filePathList;
    }

   /* @RequestMapping("/pt")
    public String pt() throws Exception {
        File file = new ClassPathResource("pic/beauty.jpg", getClass()).getFile();
        System.out.println(file.exists() + "  yessssssss~");
        return "succ";
    }*/
}
