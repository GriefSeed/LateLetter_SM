package com.hyht.LateLetter.web;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hyht.LateLetter.EnvirArgs;
import com.hyht.LateLetter.dao.BFileDao;
import com.hyht.LateLetter.dao.CountDao;
import com.hyht.LateLetter.dao.LetterDao;
import com.hyht.LateLetter.dao.UsersDao;
import com.hyht.LateLetter.dto.CountNum;
import com.hyht.LateLetter.dto.LetterWithUser;
import com.hyht.LateLetter.dto.ObjWithMsg;
import com.hyht.LateLetter.entity.BFile;
import com.hyht.LateLetter.entity.Letter;
import com.hyht.LateLetter.util.Util;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/API")
public class APIController {


    @Autowired
    ServletContext servletContext;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    BFileDao bFileDao;

    @Autowired
    CountDao countDao;

    @Autowired
    UsersDao usersDao;

    @Autowired
    LetterDao letterDao;

    private final static Logger logger = LoggerFactory.getLogger(APIController.class);

    private static Pattern NUM_JUDGE = Pattern.compile("[0-9]*");

    @RequestMapping(value = "/addPic")
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
            String dir = "";
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
        JSONObject jb = new JSONObject();
        jb.put("imgUrl", EnvirArgs.internetFileUrl + "/checkImg/" + createText + ".jpg");
        jb.put("text", createText);
        //定义线程，70秒后删除该文件

        return jb;
    }


    /**
     * 录入迟书附件—图片、视频、录音
     *
     * @return 返回FLAG
     */
    @RequestMapping(value = "/addExtraFile")
    public Object addLetter(@RequestParam("letterId") Long letterId,
                            @RequestParam("fileUrls") MultipartFile[] fileList) {

        //创建目录
        String letterPath = "\\letterExtraFile\\" + letterId;
        String filePathStr = EnvirArgs.extraFilePath + letterPath;
        File filePath = new File(filePathStr);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdirs();
        }
        ;
        Map<String, Integer> map = new HashMap<String, Integer>();
        //写入文件，返回url
        try {
            for (MultipartFile file : fileList) {
                int i = 0;
                //获取文件名和后缀
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String fileName = "\\" + ((new Date()).getTime() / 1000 + "") + i + suffix;

                File newFile = new File(filePathStr + fileName);
                newFile.createNewFile();
                OutputStream os = new FileOutputStream(newFile);
                os.write(file.getBytes());
                os.flush();
                os.close();
                //使用K-V方式存入url-type
                map.put(letterPath + fileName, Util.fileTypeJudge(suffix));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new ObjWithMsg(null, "F", "CREATE_FILE_ERROR");
        }
        //将url存入数据库
        if (!map.isEmpty()) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                bFileDao.insertBFile(new BFile(letterId, entry.getKey(), entry.getValue()));
            }

        }
        return new ObjWithMsg(null, "T", "SUCCESS");
    }


    /**
     * 页面计数功能
     *
     * @param userId
     * @return null 失败 非null 成功
     */
    @RequestMapping("/countNum")
    public Object countNum(@RequestBody Long userId) {
        try {
            CountNum countNum = new CountNum();
            countNum.setUserTime(Integer.valueOf(usersDao.queryUserRestTimeById(userId)));
            countNum.setMyReceiverNum(countDao.queryUserReceivePeopleNum(userId));
            countNum.setMyAttentionNum(countDao.queryUserAttentionNum(userId));
            countNum.setMyLetterNum(countDao.queryUserLetterNum(userId));
            countNum.setMyAcceptLetterNum(countDao.queryUserReceiveLetterNum(userId));
            countNum.setMyCollectionNum(countDao.queryUserCollectionNum(userId));
            return new ObjWithMsg(countNum, "T", "SUCCESS");
        } catch (Exception e) {
            logger.error("======================countNum============================ : ", e);
            return new ObjWithMsg(null, "F", "查询数量失败");
        }
    }


    /**
     * 搜索功能
     *
     * @param content
     * @return null 表示 搜索为空，非null，有内容
     */
    @RequestMapping("/searchFun")
    public Object searchFun(@RequestBody String content) {
        try {
            Letter tempById = null;
            List<Letter> listTempByTitle = null;
            List<Letter> aimList = new ArrayList<Letter>();
            //先用正则判断是否是数字串，先搜迟书ID，再搜标题
            Matcher isNum = NUM_JUDGE.matcher(content);
            if (isNum.matches()) {
                Long letterId = Long.valueOf(content);
                tempById = letterDao.queryLetterById(letterId);
                if (tempById != null) {
                    aimList.add(tempById);
                }
            } else {
                listTempByTitle = letterDao.queryLetterByTitle(content);
                if (listTempByTitle != null) {
                    for (Letter l : listTempByTitle) {
                        aimList.add(l);
                    }
                }
            }
            if (!aimList.isEmpty()) {
                List<LetterWithUser> letterWithUsers = new ArrayList<LetterWithUser>();
                for (Letter l : aimList) {
                    letterWithUsers.add(new LetterWithUser(usersDao.queryUserById(l.getUserId()), l));
                }
                return new ObjWithMsg(letterWithUsers, "T", "SUCCESS");
            } else {
                return new ObjWithMsg(null, "T", "SUCCESS");
            }
        } catch (Exception e) {
            logger.error("======================searchFun============================ : ", e);
            return new ObjWithMsg(null, "F", "搜索失败");
        }
    }

    @RequestMapping(value = "/test")
    public Object test(String content) throws Exception {
        try {
            Letter tempById = null;
            List<Letter> listTempByTitle = null;
            List<Letter> aimList = new ArrayList<Letter>();
            //先用正则判断是否是数字串，先搜迟书ID，再搜标题
            Matcher isNum = NUM_JUDGE.matcher(content);
            if (isNum.matches()) {
                Long letterId = Long.valueOf(content);
                tempById = letterDao.queryLetterById(letterId);
                if (tempById != null) {
                    aimList.add(tempById);
                }
            } else {
                listTempByTitle = letterDao.queryLetterByTitle(content);
                if (listTempByTitle != null) {
                    for (Letter l : listTempByTitle) {
                        aimList.add(l);
                    }
                }
            }
            if (!aimList.isEmpty()) {
                List<LetterWithUser> letterWithUsers = new ArrayList<LetterWithUser>();
                for (Letter l : aimList) {
                    letterWithUsers.add(new LetterWithUser(usersDao.queryUserById(l.getUserId()), l));
                }
                return new ObjWithMsg(letterWithUsers, "T", "SUCCESS");
            } else {
                return new ObjWithMsg(null, "T", "SUCCESS");
            }
        } catch (Exception e) {
            logger.error("======================searchFun============================ : ", e);
            return new ObjWithMsg(null, "F", "搜索失败");
        }
    }

   /* @RequestMapping("/pt")
    public String pt() throws Exception {
        File file = new ClassPathResource("pic/beauty.jpg", getClass()).getFile();
        System.out.println(file.exists() + "  yessssssss~");
        return "succ";
    }*/

}
