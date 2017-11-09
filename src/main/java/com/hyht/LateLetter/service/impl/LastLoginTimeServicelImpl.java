package com.hyht.LateLetter.service.impl;

import com.hyht.LateLetter.dao.LastLoginTimeDao;
import com.hyht.LateLetter.dao.UsersDao;
import com.hyht.LateLetter.service.LastLoginTimeServicel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LastLoginTimeServicelImpl implements LastLoginTimeServicel {

    @Autowired
    UsersDao usersDao;

    @Autowired
    LastLoginTimeDao lastLoginTimeDao;

    /**
     * 更新用户最近登陆时间时间
     *
     * @param userId
     * @param type   1 表示 非连续登陆，加30天，2 表示连续登陆，加 30 * 3 天
     * @return 0 表示失败 1 表示成功
     */
    @Override
    public int updateUserLastLoginTime(Long userId, int type) {
            if(type == 1){
                String userRestTime = usersDao.queryUserRestTimeById(userId);
                String lastLoginTime = String.valueOf(Long.valueOf(userRestTime) + 30);
                //增加用户时间
                int result = usersDao.updateUserRestTime(lastLoginTime, userId);
                if(result == 1){
                    //刷新用户最近登陆时间
                    int otherResult = lastLoginTimeDao.updateUserLastLoginTime(userId);
                    if(otherResult == 1){
                        return 1;
                    }else{
                        return 0;
                    }
                }
            }
            if(type == 2){
                String userRestTime = usersDao.queryUserRestTimeById(userId);
                String lastLoginTime = String.valueOf(Long.valueOf(userRestTime) + 30 * 3);
                //增加用户时间
                int result = usersDao.updateUserRestTime(lastLoginTime, userId);
                if(result == 1){
                    //刷新用户最近登陆时间
                    int otherResult = lastLoginTimeDao.updateUserLastLoginTime(userId);
                    if(otherResult == 1){
                        return 1;
                    }else{
                        return 0;
                    }
                }
            }
            return 0;
    }
}
