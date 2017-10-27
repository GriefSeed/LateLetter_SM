package com.hyht.LateLetter.dao;


import com.hyht.LateLetter.entity.UserRelation;
import com.hyht.LateLetter.entity.Users;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRelationDao {


    /**
     * 查询该用户的所有收信人
     * @param userId
     * @return
     */
    @Select("select u.* from LATELETTER.USER_RELATION ur,LATELETTER.USERS u where ur.master_user_id = #{userId} AND ur.relation = 2 AND ur.other_user_id = u.user_id order by ur.start_time desc")
    List<Users> queryUserAddressee(@Param("userId") Long userId);


    /**
     * 返回单个用户的主动关注列表接口
     * @param userId
     * @return
     */
    @Select("select u.* from LATELETTER.USER_RELATION ur,LATELETTER.USERS u where ur.master_user_id = #{userId} AND ur.relation = 1 AND ur.other_user_id = u.user_id order by ur.start_time desc")
    List<Users> queryUserAttention(@Param("userId") Long userId);

    /**
     * 返回单个用户的被关注列表接口
     * @param userId
     * @return
     */
    @Select("select u.* from LATELETTER.USER_RELATION ur,LATELETTER.USERS u where ur.other_user_id = #{userId} AND ur.relation = 1 AND ur.master_user_id = u.user_id order by ur.start_time desc")
    List<Users> queryUserAttentioned(@Param("userId") Long userId);


    /**
     * 添加关注 1，收信人 2，单向，插入一条记录
     * @param userRelation
     * @return
     */
    int insertAttentionRelation(UserRelation userRelation);


    /**
     * 检测用户是否与该人关系是 收信人 还是 关注，0表示两者都没有，1 表示关注，2 表示收信人, 3 两者都有
     * @param userId
     * @param otherUserId
     * @return
     */
    @Select("select ur.relation from LATELETTER.USER_RELATION ur where ur.master_user_id = #{userId} and ur.other_user_id = #{otherUserId}")
    List<Integer> selectUserRelation(@Param("userId") Long userId, @Param("otherUserId") Long otherUserId);


    /**
     * 删除用户关注、收信人,删除关注传1，删除收信人传2,
     * @param userId
     * @param otherUserId
     * @param relation
     * @return
     */
    @Delete("delete from LATELETTER.USER_RELATION ur where ur.master_user_id = #{userId} and ur.other_user_id = #{otherUserId} and relation = #{relation}")
    int deleteUserRelation(@Param("userId") Long userId, @Param("otherUserId") Long otherUserId, @Param("relation") int relation);



}
