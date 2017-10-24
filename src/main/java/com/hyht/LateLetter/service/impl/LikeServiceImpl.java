package com.hyht.LateLetter.service.impl;

import com.hyht.LateLetter.dao.LikeDao;
import com.hyht.LateLetter.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LikeServiceImpl implements LikeService {


    @Autowired
    LikeDao likeDao;

    @Override
    public int addLetterLikeNum(Long letterId, Long userId) {
        int result = 0;
        int addResult = 0;
        //先加1
        result = likeDao.addLikeNum(letterId);
        if (result == 1) {
            //再插入记录
            addResult = likeDao.addUserLike(letterId, userId);
            if (addResult == 1) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 删除迟书时调用，同时删除点赞表记录、点赞用户关系
     *
     * @param letterId
     * @return 0 表示失败， 1 表示成功
     */
    @Override
    public int deleteLetterLike(Long letterId) {
        int result = 0;
        result = likeDao.deleteLike(letterId);
        if (result == 1) {
            //删除用户-点赞记录,可能是一个人都没有点赞，没有记录也可能
            likeDao.deleteUserLike(letterId);
            return 1;

        }
        return 0;
    }


}
