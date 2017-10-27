package com.hyht.LateLetter.web;


import com.hyht.LateLetter.EnvirArgs;
import com.hyht.LateLetter.dao.LetterUserRelationDao;
import com.hyht.LateLetter.dao.UserRelationDao;
import com.hyht.LateLetter.dao.UsersDao;
import com.hyht.LateLetter.dto.ObjWithMsg;
import com.hyht.LateLetter.dto.UserIdWithOtherUserId;
import com.hyht.LateLetter.dto.UserIdWithSecretKey;
import com.hyht.LateLetter.entity.Letter;
import com.hyht.LateLetter.entity.UserRelation;
import com.hyht.LateLetter.entity.Users;
import com.hyht.LateLetter.service.UserRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/FRIEND")
public class FriendController {

    @Autowired
    UsersDao usersDao;
    @Autowired
    UserRelationDao userRelationDao;
    @Autowired
    LetterUserRelationDao letterUserRelationDao;
    @Autowired
    UserRelationService userRelationService;

    private final static Logger logger = LoggerFactory.getLogger(FriendController.class);


    /**
     * 返回单个用户的收信人列表接口
     *
     * @param userId
     * @return null 没有收信人，非null，有收信人
     */
    @RequestMapping(value = "/queryUserAddressee")
    public Object queryUserAddressee(@RequestBody Long userId) {
        List<Users> usersList;
        try {
            usersList = userRelationDao.queryUserAddressee(userId);
            if (usersList.isEmpty()) {
                return new ObjWithMsg(null, "T", "没有收信人");
            }
        } catch (Exception e) {
            logger.error("========================queryUserReceiveLetter=================================: ", e);
            return new ObjWithMsg(null, "F", "查询收信人接口错误");
        }
        for (Users users : usersList) {
            users.setShowImg(EnvirArgs.internetFileUrl + users.getShowImg());
        }
        return new ObjWithMsg(usersList, "T", "SUCCESS");
    }

    /**
     * 返回单个用户的关注列表接口
     *
     * @param userId
     * @return null 没有关注人，非null，有关注人
     */
    @RequestMapping(value = "/queryUserAttention")
    public Object queryUserAttention(@RequestBody Long userId) {
        List<Users> usersList;
        try {
            usersList = userRelationDao.queryUserAttention(userId);
            if (usersList.isEmpty()) {
                return new ObjWithMsg(null, "T", "没有关注");
            }
        } catch (Exception e) {
            logger.error("========================queryUserAttention=================================: ", e);
            return new ObjWithMsg(null, "F", "查询关注接口错误");
        }
        for (Users users : usersList) {
            users.setShowImg(EnvirArgs.internetFileUrl + users.getShowImg());
        }
        return new ObjWithMsg(usersList, "T", "SUCCESS");
    }


    /**
     * 返回单个用户的被关注列表接口
     *
     * @param userId
     * @return null 没人关注你，非null，有人关注你
     */
    @RequestMapping(value = "/queryUserAttentioned")
    public Object queryUserAttentioned(@RequestBody Long userId) {
        List<Users> usersList;
        try {
            usersList = userRelationDao.queryUserAttentioned(userId);
            if (usersList.isEmpty()) {
                return new ObjWithMsg(null, "T", "没有人关注你");
            }
        } catch (Exception e) {
            logger.error("========================queryUserAttentioned=================================: ", e);
            return new ObjWithMsg(null, "F", "查询被关注接口错误");
        }
        for (Users users : usersList) {
            users.setShowImg(EnvirArgs.internetFileUrl + users.getShowImg());
        }
        return new ObjWithMsg(usersList, "T", "SUCCESS");
    }

    /**
     * 返回收到迟书接口
     *
     * @param userId
     * @return null 没有收信， 非null 有收信
     */
    @RequestMapping(value = "/queryUserReceiveLetter")
    public Object queryUserReceiveLetters(@RequestBody Long userId) {
        List<Letter> letters;
        try {
            letters = letterUserRelationDao.queryUserReceiveLetter(userId);
            if (letters.isEmpty()) {
                return new ObjWithMsg(null, "T", "没有收信");
            }
        } catch (Exception e) {
            logger.error("========================queryUserReceiveLetters=================================: ", e);
            return new ObjWithMsg(null, "F", "查询被收信接口错误");
        }
        return new ObjWithMsg(letters, "T", "SUCCESS");
    }


    /**
     * 添加收信人接口
     *
     * @param userIdWithOtherUserId
     * @return 0 失败， 1 成功
     */
    @RequestMapping(value = "/addReceive")
    public Object addReceive(@RequestBody UserIdWithOtherUserId userIdWithOtherUserId) {
        int result = 0;
        UserRelation userRelationTemp = new UserRelation(userIdWithOtherUserId.getUserId(), userIdWithOtherUserId.getOtherUserId(), 2, new Date());
        try {
            result = userRelationDao.insertAttentionRelation(userRelationTemp);
            if (result != 1) {
                return new ObjWithMsg(0, "F", "新增收信人失败");
            }
        } catch (Exception e) {
            logger.error("========================addReceive=================================: ", e);
            return new ObjWithMsg(0, "F", "新增收信人失败");
        }
        return new ObjWithMsg(result, "T", "SUCCESS");
    }


    /**
     * 删除收信人接口
     *
     * @param userIdWithOtherUserId
     * @return 0 失败， 1 成功
     */
    @RequestMapping(value = "/deleteReceive")
    public Object deleteReceive(@RequestBody UserIdWithOtherUserId userIdWithOtherUserId) {
        int result = 0;
        try {
            result = userRelationDao.deleteUserRelation(userIdWithOtherUserId.getUserId(), userIdWithOtherUserId.getOtherUserId(), 2);
            if (result != 1) {
                return new ObjWithMsg(0, "F", "删除收信人失败");
            }
        } catch (Exception e) {
            logger.error("========================deleteReceive=================================: ", e);
            return new ObjWithMsg(0, "F", "删除收信人失败");
        }
        return new ObjWithMsg(result, "T", "SUCCESS");
    }

    /**
     * 主动添加关注人接口
     *
     * @param userIdWithOtherUserId
     * @return 0 失败， 1 成功
     */
    @RequestMapping(value = "/addAttention")
    public Object addAttention(@RequestBody UserIdWithOtherUserId userIdWithOtherUserId) {
        int result = 0;
        UserRelation userRelationTemp = new UserRelation(userIdWithOtherUserId.getUserId(), userIdWithOtherUserId.getOtherUserId(), 1, new Date());
        try {
            result = userRelationDao.insertAttentionRelation(userRelationTemp);
            if (result != 1) {
                return new ObjWithMsg(0, "F", "新增关注人失败");
            }
        } catch (Exception e) {
            logger.error("========================addAttention=================================: ", e);
            return new ObjWithMsg(0, "F", "新增关注人失败");
        }
        return new ObjWithMsg(result, "T", "SUCCESS");
    }

    /**
     * 删除关注人接口
     *
     * @param userIdWithOtherUserId
     * @return 0 表示失败，1 表示成功
     */
    @RequestMapping(value = "/deleteAttention")
    public Object deleteAttention(@RequestBody UserIdWithOtherUserId userIdWithOtherUserId) {
        int result = 0;
        try {
            result = userRelationDao.deleteUserRelation(userIdWithOtherUserId.getUserId(), userIdWithOtherUserId.getOtherUserId(), 1);
            if (result != 1) {
                return new ObjWithMsg(0, "F", "删除关注人失败");
            }
        } catch (Exception e) {
            logger.error("========================deleteAttention=================================: ", e);
            return new ObjWithMsg(0, "F", "删除关注人失败");
        }
        return new ObjWithMsg(result, "T", "SUCCESS");
    }

    /**
     * 检测是否符合某个用户密钥接口
     *
     * @param userIdWithSecretKey
     * @return 0 不符合 1 符合
     */
    @RequestMapping(value = "/checkUserSecretKey")
    public Object checkUserSecretKey(@RequestBody UserIdWithSecretKey userIdWithSecretKey) {
        Users uTemp = null;
        try {
            uTemp = usersDao.queryUserById(userIdWithSecretKey.getUserId());
            if (uTemp != null && uTemp.getSecretKey().equals(userIdWithSecretKey.getSecretKey())) {
                return new ObjWithMsg(1, "T", "SUCCESS");
            }
            return new ObjWithMsg(0, "T", "SUCCESS");
        } catch (Exception e) {
            logger.error("========================checkUserSecretKey=================================: ", e);
            return new ObjWithMsg(null, "F", "检测密钥接口失败");
        }
    }


    /**
     * 判断该人与用户是否关注、是否收信人接口（合为一个）
     *
     * @param userIdWithOtherUserId
     * @return 0 表示两者都没有，1 表示关注，2 表示收信人, 3 两者都有
     */
    @RequestMapping(value = "/checkUserAttentionAndReceive")
    public Object checkUserAttentionAndReceive(@RequestBody UserIdWithOtherUserId userIdWithOtherUserId) {
        int result;
        try {
            result = userRelationService.checkUserRelation(userIdWithOtherUserId.getUserId(), userIdWithOtherUserId.getOtherUserId());
        } catch (Exception e) {
            logger.error("========================checkUserAttentionAndReceive=================================: ", e);
            return new ObjWithMsg(null, "F", "检测接口失败");
        }
        return new ObjWithMsg(result, "T", "SUCCESS");
    }

    @RequestMapping(value = "/test")
    public Object test(UserIdWithOtherUserId userIdWithOtherUserId) {
        int result;
        try {
            result = userRelationService.checkUserRelation(userIdWithOtherUserId.getUserId(), userIdWithOtherUserId.getOtherUserId());
        } catch (Exception e) {
            logger.error("========================checkUserAttentionAndReceive=================================: ", e);
            return new ObjWithMsg(null, "F", "检测接口失败");
        }
        return new ObjWithMsg(result, "T", "SUCCESS");
    }

}
