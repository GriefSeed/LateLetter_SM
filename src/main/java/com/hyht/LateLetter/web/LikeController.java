package com.hyht.LateLetter.web;


import com.hyht.LateLetter.dao.LikeDao;
import com.hyht.LateLetter.dto.LetterIdWithUserId;
import com.hyht.LateLetter.dto.ObjWithMsg;
import com.hyht.LateLetter.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/LIKE")
public class LikeController {

    @Autowired
    LikeDao likeDao;

    @Autowired
    LikeService likeService;

    private final static Logger logger = LoggerFactory.getLogger(LikeController.class);


    /**
     * 增加点赞表记录，在创建迟书时要一起调用，根据返回的letterId来创建，否则后续会找不到点赞数量
     * @param letterId
     * @return 0 表示成功，1 表示失败
     */
    @RequestMapping(value = "/addLetterLike")
    public Object addLetterLike(@RequestBody Long letterId){
        int result = 0;
        try {
            result = likeDao.addLike(letterId);
        }catch (Exception e){
            logger.error("queryLetterLikeNum: ", e);
            return new ObjWithMsg(null, "F", "新增点赞失败");
        }
        return new ObjWithMsg(result, "T", "SUCCESS");
    }


    /**
     * 查询点赞数, PS:记录随迟书的创建而创建
     * @param letterId
     * @return null 表示失败，0 或 以上数字表示成功
     */
    @RequestMapping(value = "/queryLetterLikeNum")
    public Object queryLetterLikeNum(@RequestBody Long letterId){
        Long likeNum = null;
        try {
            likeNum = likeDao.queryLikeNum(letterId);
        }catch (Exception e){
            logger.error("queryLetterLikeNum: ", e);
            return new ObjWithMsg(null, "F", "获取点赞数失败");
        }
        return new ObjWithMsg(likeNum, "T", "SUCCESS");
    }

    /**
     * 点赞 + 1， 同时创建用户—迟书记录 ,PS:记录随迟书的创建而创建
     * @param letterIdWithUserId
     * @return 0 表示失败，1表示成功
     */
    @RequestMapping(value = "/addLetterLikeNum")
    public Object addLetterLikeNum(@RequestBody LetterIdWithUserId letterIdWithUserId){
        int result = 0;
        try {
            result = likeService.addLetterLikeNum(letterIdWithUserId.getLetterId(), letterIdWithUserId.getUserId());
        }catch (Exception e){
            logger.error("addLetterLikeNum: ", e);
            return new ObjWithMsg(null, "F", "用户点赞失败");
        }
        return new ObjWithMsg(result, "T", "SUCCESS");
    }


    /**
     * 检测用户是否已经点赞
     * @param letterIdWithUserId
     * @return 0 表示没有点赞，1 表示已经点赞
     */
    @RequestMapping(value = "/checkUserLike")
    public Object checkUserLike(@RequestBody LetterIdWithUserId letterIdWithUserId){
        int result = 0;
        try {
            result = likeDao.queryUserLikeOrNot(letterIdWithUserId.getLetterId(), letterIdWithUserId.getUserId());
        }catch (Exception e){
            logger.error("addLetterLikeNum: ", e);
            return new ObjWithMsg(null, "F", "未能确认是否点赞");
        }
        return new ObjWithMsg(result, "T", "SUCCESS");
    }


    /**
     * 在删除迟书时，删除相对应的点赞表记录和点赞—用户关系
     * @param letterId
     * @return 0 表示失败， 1表示成功
     */
    @RequestMapping(value = "/deleteLetterLike")
    public Object deleteLetterLike(@RequestBody Long letterId){
        int result = 0;
        try{
            result = likeService.deleteLetterLike(letterId);
        }catch (Exception e){
            logger.error("deleteLetterLike: ", e);
            return new ObjWithMsg(null, "F", "删除点赞失败");
        }
        return new ObjWithMsg(result, "T", "SUCCESS");
    }

    @RequestMapping(value = "/test")
    public Object test(Long letterId) throws Exception {
        int result = 0;
        try{
            result = likeService.deleteLetterLike(letterId);
        }catch (Exception e){
            logger.error("deleteLetterLike: ", e);
            return new ObjWithMsg(null, "F", "删除点赞失败");
        }
        return new ObjWithMsg(result, "T", "SUCCESS");
    }

}
