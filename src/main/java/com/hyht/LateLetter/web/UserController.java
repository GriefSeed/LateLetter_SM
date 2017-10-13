package com.hyht.LateLetter.web;


import com.hyht.LateLetter.EnvirArgs;
import com.hyht.LateLetter.dao.UsersDao;
import com.hyht.LateLetter.dto.ObjWithMsg;
import com.hyht.LateLetter.dto.UserIdWithShowImg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("/USER")
public class UserController {

    @Autowired
    UsersDao usersDao;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    /**
     * 统一放入showImg文件夹下，统一以userId_showImg.png命名
     * @param userIdWithShowImg
     * @return 返回新的头像图片地址，供前台刷新
     */
    @RequestMapping("/changeShowImg")
    public Object changeShowImg(@RequestBody UserIdWithShowImg userIdWithShowImg){
        String userId = userIdWithShowImg.getUserId();
        String img = userIdWithShowImg.getShowImg();
        String suffix = "_showImg.png";
        String showImgPath = "\\showImg\\" + userId + suffix;
        //先存储或替换图片文件
        String filePath = EnvirArgs.extraFilePath + showImgPath;
        File file = new File(filePath);
        try {
            if(file.exists()){
                //重写file
                OutputStream os = new FileOutputStream(file);
                os.write(Base64Utils.decodeFromString(img));
                os.flush();
                os.close();
            }
            else{
                //新建文件
                file.createNewFile();
                OutputStream os = new FileOutputStream(file);
                os.write(Base64Utils.decodeFromString(img));
                os.flush();
                os.close();
            }
        } catch (FileNotFoundException e) {
            logger.error("changeShowImg: ",e);
            return new ObjWithMsg("","F","FileNotFoundException");
        } catch (IOException e) {
            logger.error("changeShowImg: ",e);
            return new ObjWithMsg("","F","IOException");
        }
        //替换数据库数据，写入网络路径，正斜杠
        usersDao.updataUserShowImg(showImgPath.replaceAll("\\\\","/"), Long.valueOf(userId));

        //返回新的url地址
        return new ObjWithMsg(EnvirArgs.internetFileUrl + showImgPath.replaceAll("\\\\","/"),"T","SUCCESS");
    }


}
