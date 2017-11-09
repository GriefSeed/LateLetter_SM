package com.hyht.LateLetter.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface LastLoginTimeDao {


    @Select("select llt.last_login_time from LATELETTER.LAST_LOGIN_TIME llt where llt.userId = #{userId}")
    Date queryUserLastLoginTime(Long userId);

    /**
     * 注册时新插入用户最近登陆时间
     * @param userId
     * @return
     */
    @Insert("insert into LATELETTER.LAST_LOGIN_TIME(userId, last_login_time) values (#{userId}, sysdate)")
    int insertUserLastLoginTime(Long userId);

    @Update("update LATELETTER.LAST_LOGIN_TIME llt SET llt.last_login_time = sysdate where llt.userId = #{userId}")
    int updateUserLastLoginTime(Long userId);
}
