package com.hyht.LateLetter.service.impl;


import com.hyht.LateLetter.dao.UserRelationDao;
import com.hyht.LateLetter.service.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserRelationServiceImpl implements UserRelationService{

    @Autowired
    UserRelationDao userRelationDao;


    /**
     * 检测用户是否两者都没、关注、收信人、还是两者都有
     *
     * @param userId      当前用户
     * @param otherUserId 受检测的用户
     * @return 0 表示两者都没有，1 表示关注，2 表示收信人, 3 两者都有
     */
    @Override
    public int checkUserRelation(Long userId, Long otherUserId) {
        //查找记录
        List<Integer> relationList = userRelationDao.selectUserRelation(userId, otherUserId);
        //检测查找记录内是否有1、2
        if(!relationList.isEmpty()){
            if(relationList.contains(1) && relationList.contains(2)){
                return 3;
            }
            if(relationList.contains(1)){
                return 1;
            }
            if(relationList.contains(2)){
                return 2;
            }
            return 0;
        }else{
            return 0;
        }
    }
}
