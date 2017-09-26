package com.hyht.LateLetter.dao;

import com.hyht.LateLetter.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao {


    @Select("select * from Users where user_id=#{userId}")
    Users queryUserById(@Param("userId") long userId);

    @Select("select * from Users where phone_num=#{phoneNum}")
    Users queryUserByPhoneNum(@Param("phoneNum") String phoneNum);

    /**
     *
     * @param users
     * @return 返回影响的行数，0为插入失败
     * @Insert("INSERT INTO Users (user_id, nickname, user_password, sex, show_img, rest_time, phone_num, real_name, id_card, status) VALUES (#{userId}, #{nickname, jdbcType=VARCHAR}, #{userPassword}, #{sex}, #{showImg, jdbcType=VARCHAR}, #{restTime, jdbcType=VARCHAR}, #{phoneNum, jdbcType=VARCHAR}, #{realName, jdbcType=VARCHAR}, #{idCard, jdbcType=VARCHAR}, #{status})")
     @SelectKey(statement = "select LATELETTER.USERS_S.nextval from dual", keyProperty = "userId", before = true, resultType = long.class)
     */
    int insertUser(Users users);

    /**
     * 对用户的剩余时间进行操作,刷新用户的剩余时间
     * @param users
     * @return
     */
    @Update("update Users SET rest_time=#{restTime} where user_id=#{userId}")
    int updateUserRestTime(Users users);

    /**
     * 查用户剩余时间
     * @param userId
     * @return
     */
    @Select("select rest_time from USERS where user_id=#{userId}")
    String queryUserRestTimeById(@Param("userId") long userId);


}
