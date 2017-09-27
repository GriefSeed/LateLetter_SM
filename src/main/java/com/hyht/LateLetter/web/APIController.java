package com.hyht.LateLetter.web;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hyht.LateLetter.EnvirArgs;
import com.hyht.LateLetter.entity.Users;
import com.hyht.LateLetter.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/API")
public class APIController {


    @Autowired
    ServletContext servletContext;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping(value = "/addPic", method = RequestMethod.POST)
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
            File file = new File(EnvirArgs.extraFilePath + "\\content_pic\\" + imgName + suffix);
            file.createNewFile();
            OutputStream os = new FileOutputStream(file);
            os.write(bs);
            os.flush();
            os.close();
            filePathList.add(Util.getServerIp() + ":8081/pic/" + imgName + suffix);
        }
        return filePathList;
    }


    @RequestMapping("/getCheckImage")
    public Object getCheckImage() {
        String createText = defaultKaptcha.createText();
        OutputStream os = null;
        try {
            //要先创建文件夹，否则会报路径错误
            File file = new File(EnvirArgs.extraFilePath + "\\checkImg\\" + createText + ".jpg");
            file.createNewFile();
            os = new FileOutputStream(file);
            //生成图片
            BufferedImage createImg = defaultKaptcha.createImage(createText);
            ImageIO.write(createImg, "jpg", os);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*JSONPObject jb = new JSONPObject();
        jb.put("imgUrl",EnvirArgs.extraFileUrl + "/checkImg/" + createText + ".jpg" );
        jb.put("text",createText);*/
        //定义线程，70秒后删除该文件

        return "jb";
    }
   /* @RequestMapping("/pt")
    public String pt() throws Exception {
        File file = new ClassPathResource("pic/beauty.jpg", getClass()).getFile();
        System.out.println(file.exists() + "  yessssssss~");
        return "succ";
    }*/

    @RequestMapping(value = "/test")
    public Object test(@RequestBody Users u) throws Exception {
        System.out.println("ddd");
        return u.toString();
    }
}
