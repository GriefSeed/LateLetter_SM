package com.hyht.LateLetter.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * 用于点赞功能
 */
@Repository
public interface LikeDao {


    /**
     * 查询用户是否已经点赞（已在表中存在关系）
     * @param letterId
     * @return 1表示已有，0表示没有
     */
    @Select("select count(*) from LATELETTER.USER_LIKE ul where ul.letter_id = #{letterId} and ul.user_id = #{userId}")
    int queryUserLikeOrNot(@Param("letterId") Long letterId, @Param("userId") Long userId);


    /**
     * 查询单封迟书的点赞数
     * @param letterId
     * @return 返回点赞数
     */
    @Select("select ln.like_num from LATELETTER.LIKE_NUM ln where ln.letter_id = #{letterId}")
    Long queryLikeNum(@Param("letterId") Long letterId);


    /**
     * 在创建迟书同时，新建一条点赞记录
     * @param letterId
     * @return
     */
    @Insert("insert into LATELETTER.LIKE_NUM(letter_id) values (#{letterId})")
    int addLike(@Param("letterId") Long letterId);


    /**
     * 在删除迟书时，同时删除相对应的点赞记录
     */
    @Delete("delete from LATELETTER.LIKE_NUM ln where ln.letter_id = #{letterId}")
    int deleteLike(@Param("letterId") Long letterId);

    /**
     * 用户点赞一次，数量 + 1
     * @param letterId
     * @return
     */
    @Update("update LATELETTER.LIKE_NUM SET like_num = like_num + 1 where letter_id = #{letterId}")
    int addLikeNum(@Param("letterId") Long letterId);


    /**
     * 用户点赞一次，增加迟书—用户关联记录，用于防止用户重复点赞
     * @param letterId
     * @param userId
     * @return
     */
    @Insert("insert into LATELETTER.USER_LIKE(letter_id, user_id) values (#{letterId}, #{userId})")
    int addUserLike(@Param("letterId") Long letterId, @Param("userId") Long userId);

    /**
     * 删除迟书时，同时删除迟书—用户点赞关系
     * @param letterId
     * @return
     */
    @Delete("delete from LATELETTER.USER_LIKE ul where ul.letter_id = #{letterId}")
    int deleteUserLike(@Param("letterId") Long letterId);
}
