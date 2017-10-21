package com.hyht.LateLetter.web;


import com.hyht.LateLetter.EnvirArgs;
import com.hyht.LateLetter.dao.BFileDao;
import com.hyht.LateLetter.dao.LetterDao;
import com.hyht.LateLetter.dao.LetterUserRelationDao;
import com.hyht.LateLetter.dao.UsersDao;
import com.hyht.LateLetter.dto.*;
import com.hyht.LateLetter.entity.*;
import com.hyht.LateLetter.service.LetterService;
import com.hyht.LateLetter.service.LetterUserRelationService;
import com.hyht.LateLetter.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@RestController
@RequestMapping("/")
public class MainController {


    @Autowired
    UsersDao usersDao;

    @Autowired
    LetterDao letterDao;

    @Autowired
    BFileDao bFileDao;

    @Autowired
    UsersService usersService;

    @Autowired
    LetterUserRelationDao letterUserRelationDao;

    @Autowired
    LetterUserRelationService letterUserRelationService;

    @Autowired
    LetterService letterService;


    private final static Logger logger = LoggerFactory.getLogger(MainController.class);

    /**
     * 登陆
     *
     * @param u
     * @return
     */
    @RequestMapping("/login")
    public Object login(@RequestBody Users u) {
        Users users = usersDao.queryUserByPhoneNum(u.getPhoneNum());

        if (users != null) {
            if (u.getUserPassword().equals(users.getUserPassword())) {
                return new ObjWithMsg(users, "T", "SUCCESS");
            } else {
                return new ObjWithMsg(users, "F", "PASSWORD_ERROR");
            }
        }
        return new ObjWithMsg(null, "F", "USER_NO_EXIST");
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestBody Users u) {
        Users userTemp = new Users(u.getUserPassword(), u.getPhoneNum());
        //首次用户注册，手机号码即用户名
        userTemp.setNickname(userTemp.getPhoneNum());
        //头像默认
        userTemp.setShowImg("/showImg/public_img.png");
        int result;
        try {
            result = usersService.regiter(userTemp);
            userTemp.setShowImg(EnvirArgs.internetFileUrl + userTemp.getShowImg());
        } catch (Exception e) {
            logger.error("register: ", e);
            return new ObjWithMsg(null, "F", "REGISTER_ERROR");
        }
        if (result == 1) {
            return new ObjWithMsg(userTemp, "T", "SUCCESS");
        } else {
            return new ObjWithMsg(null, "F", "PHONE_EXISTED");
        }

    }

    /**
     * 仅录入书信主体，没有附件文件
     *
     * @param letter
     * @return 书信主体ID
     */
    @RequestMapping(value = "/addLetter")
    public Object addLetter(@RequestParam("extraData") Letter letter) {
        try {
            letterDao.insertLetter(letter);
        } catch (Exception e) {
            logger.error("addLetter_controller_error", e);
            return new ObjWithMsg(null, "F", "INSERT_LETTER_MAIN_ERROR");
        }

        return new ObjWithMsg(letter.getLetterId(), "T", "SUCCESS");
    }


    /**
     * 录入迟书主体和附件文件, PS:还未扣除用户的时间
     *
     * @param lwf 书信主体 + 图片附件
     * @return 书信主体ID
     */
    @RequestMapping(value = "/addLetterWithExtraFile")
    public Object addLetterWithExtraFile(@RequestBody LetterWithFiles lwf) {
        Letter letter = lwf.getLetter();
        String[] fileList = lwf.getPicList();

        /*System.out.println(letter.toString() + " bitch");
        for (String pic : fileList) {
            System.out.println(pic.toString() + "meimeimei");
        }*/


        //录入文件附件，先检测文件是否存在，再录入迟书主体
        if (fileList != null || fileList.length > 0) {
            try {
                //录入letter，获取letterId，然后对文件进行存储
                letterDao.insertLetter(letter);
            } catch (Exception e) {
                e.printStackTrace();
                return new ObjWithMsg(null, "F", "INSERT_LETTER_MAIN_ERROR");
            }

            //创建目录
            String letterPath = "\\letterExtraFile\\" + letter.getLetterId();
            String filePathStr = EnvirArgs.extraFilePath + letterPath;
            File filePath = new File(filePathStr);
            if (!filePath.exists() && !filePath.isDirectory()) {
                filePath.mkdirs();
            }
            Map<String, Integer> map = new HashMap<String, Integer>();
            //写入文件，返回url
            try {
                int i = 0;
                for (String pic : fileList) {
                    //直接PNG后缀
                    //String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                    String fileName = "\\" + ((new Date()).getTime() / 1000 + "") + i++ + ".png";

                    File newFile = new File(filePathStr + fileName);
                    newFile.createNewFile();
                    OutputStream os = new FileOutputStream(newFile);
                    os.write(Base64Utils.decodeFromString(pic));
                    os.flush();
                    os.close();
                    //使用K-V方式存入url-type, 1表示图片,注意这里要存入网络地址，不是硬盘相对地址，使用正斜杠
                    map.put((letterPath + fileName).replaceAll("\\\\", "/"), 1);
                }

            } catch (IOException e) {
                logger.error("addLetterWithExtraFile_CONTROLLER_ERROR", e);
            }
            //将url存入数据库
            if (!map.isEmpty()) {
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    bFileDao.insertBFile(new BFile(letter.getLetterId(), entry.getKey(), entry.getValue()));
                }
            }
            return new ObjWithMsg(letter.getLetterId(), "T", "SUCCESS");
        } else {
            return new ObjWithMsg(null, "F", "NO_FILE_UPLOAD");
        }
    }

    //查询用户个人所有迟书, 仅用作列表展示
    @RequestMapping(value = "/queryUsersLetter")
    public Object queryUsersLetter(@RequestBody long userId) {
        List<Letter> lList = letterDao.queryLetterByUserId(userId);
        if (!lList.isEmpty()) {
            return new ObjWithMsg(lList, "T", "SUCCESS");
        } else {
            return new ObjWithMsg(null, "F", "NO_DATA_QUERY");
        }
    }

    //查询单封迟书的详细信息, 暂时只有图片
    @RequestMapping(value = "/querySingleLetter")
    public Object querySingleLetter(@RequestBody String letterId) {
        Letter l = letterDao.queryLetterById(Integer.valueOf(letterId));
        if (l != null) {
            List<BFile> fileList = bFileDao.querySingleLetterFiles(Integer.valueOf(letterId));
            Map<String, Integer> files;
            if (!fileList.isEmpty()) {
                files = new HashMap<String, Integer>();
                for (BFile bFile : fileList) {
                    files.put(EnvirArgs.internetFileUrl + bFile.getFileUrl(), bFile.getFileType());
                }
            } else {
                files = null;
            }
            return new ObjWithMsg(new ReturnLetterWithFiles(l, files), "T", "SUCCESS");
        } else {
            return new ObjWithMsg(null, "F", "NO_DATA_QUERY");
        }

    }


    /**
     * 查询公开信到期和或未到期
     *
     * @param deadlineFlag
     * @return 返回书信列表
     */
    @RequestMapping(value = "/queryPublicLetter")
    public Object queryPublicLetter(@RequestBody String deadlineFlag) {
        List<Letter> letters = null;
        List<LetterWithUser> letterWithUsers = null;
        try {
            //查询已到期的
            if (deadlineFlag.equals("1")) {
                letters = letterDao.queryPublicLetterAndBefore();
                if(letters != null && !letters.isEmpty()){
                    letterWithUsers = new ArrayList<LetterWithUser>();
                    for(Letter letter : letters){
                        letterWithUsers.add(new LetterWithUser(usersDao.queryUserById(letter.getUserId()), letter));
                    }
                }
            }
            //查询未到期的
            if (deadlineFlag.equals("0")) {
                letters = letterDao.queryPublicLetterAndAfter();
                if(letters != null && !letters.isEmpty()){
                    letterWithUsers = new ArrayList<LetterWithUser>();
                    for(Letter letter : letters){
                        letterWithUsers.add(new LetterWithUser(usersDao.queryUserById(letter.getUserId()), letter));
                    }
                }
            }
            return new ObjWithMsg(letterWithUsers, "T", "SUCCESS");
        } catch (Exception e) {
            logger.error("queryLetterByPublicFlag: ", e);
            return new ObjWithMsg(null, "F", "QUERY_LETTER_BY_PUBLICFLAG_ERROR");
        }
    }


    /**
     * 增加收藏关系
     * @param letterCollection
     * @return
     */
    @RequestMapping(value = "/addCollection")
    public Object addCollection(@RequestBody LetterCollection letterCollection){
        // 1表示是收藏关系
        LetterUserRelation letterUserRelation = new LetterUserRelation(letterCollection.getLetterId(), letterCollection.getUserId(), 1);
        try{
            int result = letterUserRelationDao.insertLetterUserRelation(letterUserRelation);
            if(result != 1){
                throw new Exception("ADDCOLLECTION_RESULT_NOT_ONLY_ONE");
            }
        }catch (Exception e){
            logger.error("addCollection: ", e);
            return new ObjWithMsg(null, "F", "INSERT_ERROR");
        }
        return new ObjWithMsg(null, "T", "SUCCESS");
    }


    /**
     * 删除收藏关系
     * @param letterCollection
     * @return
     */
    @RequestMapping(value = "/deleteCollection")
    public Object deleteCollection(@RequestBody LetterCollection letterCollection){
        try{
            int result = letterUserRelationDao.deleteLetterUserCollection(letterCollection.getLetterId(), letterCollection.getUserId());
            if(result != 1){
                throw new Exception("DELETECOLLECTION_RESULT_NOT_ONLY_ONE");
            }
        }catch (Exception e){
            logger.error("deleteCollection: ", e);
            return new ObjWithMsg(null, "F", "DELETE_ERROR");
        }
        return new ObjWithMsg(null, "T", "SUCCESS");

    }

    @RequestMapping(value = "/deleteUserCollectionList")
    public Object deleteUserCollectionList(@RequestBody LetterCollectionList letterCollectionList){
        try {
            int result = letterUserRelationService.deleteUserCollectionList(letterCollectionList.getUserId(), letterCollectionList.getLetterId());
            if(result != 1){
                return new ObjWithMsg(null, "F", "DELETEUSERCOLLECTIONLIST_ERROR");
            }
        }catch (Exception e){
            logger.error("deleteUserCollectionList:", e);
        }
        return new ObjWithMsg(null, "T", "SUCCESS");
    }


    /**
     * 查询用户收藏集
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryUserCollection")
    public Object queryUserCollection(@RequestBody Long userId){
        List<Long> userCollectionList = null;
        try{
            userCollectionList = letterUserRelationDao.queryCollectionByUserId(userId);
            if(userCollectionList.isEmpty()){
                return new ObjWithMsg(null, "T", "NO_DATA_FOUND");
            }
        }catch (Exception e){
            logger.error("queryUserCollection: ", e);
            return new ObjWithMsg(null, "F", "QUERYUSERCOLLECTION_ERROR");
        }
        return new ObjWithMsg(userCollectionList, "T", "SUCCESS");

    }

    /**
     * 查询收藏集的所有迟书
     * @param userId
     * @return
     */
    @RequestMapping(value = "/queryLettersCollection")
    public Object queryLettersCollection(@RequestBody String userId){
        List<Letter> letters = null;
        List<LetterWithUser> letterWithUsers = null;
        try{
            letters = letterUserRelationDao.queryCollectionLetters(Long.valueOf(userId));
            if(letters != null && !letters.isEmpty()){
                letterWithUsers = new ArrayList<LetterWithUser>();
                for(Letter letter : letters){
                    letterWithUsers.add(new LetterWithUser(usersDao.queryUserById(letter.getUserId()), letter));
                }
                return new ObjWithMsg(letterWithUsers, "T", "SUCCESS");
            }
        }catch (Exception e){
            logger.error("queryLettersCollection: ", e);
            return new ObjWithMsg(null, "F", "QUERYLETTERSCOLLECTION_ERROR");
        }
        return new ObjWithMsg(null, "T", "NO_DATA_FOUND");

    }

    /**
     * 检测该封迟书和用户是否有收藏关系
     * @param letterCollection
     * @return T代表有，F代表无
     */
    @RequestMapping(value = "/checkCollectionOrNot")
    public Object checkCollectionOrNot(@RequestBody LetterCollection letterCollection){
        try{
            LetterUserRelation l = letterUserRelationDao.queryCollectionByLetterIdAndUserId(letterCollection.getLetterId(), letterCollection.getUserId());
            if(l == null){
                return new ObjWithMsg(0, "T", "NO_COLLECTION_FOUND");
            }
            else{
                return new ObjWithMsg(1, "T", "SUCCESS");
            }
        }catch (Exception e){
            logger.error("queryLettersCollection: ", e);
            return new ObjWithMsg(null, "F", "QUERYLETTERSCOLLECTION_ERROR");
        }

    }




    @RequestMapping(value = "/test")
    public Object test(String deadlineFlag) throws Exception {
        List<Letter> letters = null;
        List<LetterWithUser> letterWithUsers = null;
        try {
            //查询已到期的
            if (deadlineFlag.equals("1")) {
                letters = letterDao.queryPublicLetterAndBefore();
                if(letters != null && !letters.isEmpty()){
                    letterWithUsers = new ArrayList<LetterWithUser>();
                    for(Letter letter : letters){
                        letterWithUsers.add(new LetterWithUser(usersDao.queryUserById(letter.getUserId()), letter));
                    }
                }
            }
            //查询未到期的
            if (deadlineFlag.equals("0")) {
                letters = letterDao.queryPublicLetterAndAfter();
                if(letters != null && !letters.isEmpty()){
                    letterWithUsers = new ArrayList<LetterWithUser>();
                    for(Letter letter : letters){
                        letterWithUsers.add(new LetterWithUser(usersDao.queryUserById(letter.getUserId()), letter));
                    }
                }
            }
            return new ObjWithMsg(letterWithUsers, "T", "SUCCESS");
        } catch (Exception e) {
            logger.error("queryLetterByPublicFlag: ", e);
            return new ObjWithMsg(null, "F", "QUERY_LETTER_BY_PUBLICFLAG_ERROR");
        }
    }

    @RequestMapping(value = "/jsonTest")
    public Object jsonTest(@RequestBody Users u) throws Exception {
        System.out.println("jsonTest");
        return u;
    }

}
