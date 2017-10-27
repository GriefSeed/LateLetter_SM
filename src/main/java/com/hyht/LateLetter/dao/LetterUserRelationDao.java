package com.hyht.LateLetter.dao;

import com.hyht.LateLetter.entity.Letter;
import com.hyht.LateLetter.entity.LetterUserRelation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LetterUserRelationDao {

    int insertLetterUserRelation(LetterUserRelation letterUserRelation);


    /**
     * 删除收藏关系
     * @param letterId
     * @param userId
     * @return
     */
    @Delete("delete from LETTER_USER_RELATION where letter_id = #{letterId} AND user_id=#{userId} AND relation=1")
    int deleteLetterUserCollection(@Param("letterId") Long letterId, @Param("userId") Long userId);



    /**
     * 查询用户收藏集
     * @param userId
     * @return
     */
    @Select("select LETTER_USER_RELATION.letter_id from LETTER_USER_RELATION where user_id=#{userId} AND relation=1")
    List<Long> queryCollectionByUserId(@Param("userId") Long userId);


    /**
     * 根据用户ID，查询详细的迟书列表
     * @param userId
     * @return
     */
    @Select("select l.* from LETTER l, LETTER_USER_RELATION lur where l.letter_id = lur.letter_id AND lur.user_id = #{userId} ORDER BY lur.start_time DESC")
    List<Letter> queryCollectionLetters(@Param("userId") Long userId);


    /**
     * 根据用户ID和迟书ID查询相应的单个收藏
     * @param letterId
     * @param userId
     * @return
     */
    @Select("select * from LETTER_USER_RELATION where user_id=#{userId} AND letter_id=#{letterId} AND relation=1")
    LetterUserRelation queryCollectionByLetterIdAndUserId(@Param("letterId") Long letterId, @Param("userId") Long userId);


    /**
     * 查询用户收信箱
     * @param userId
     * @return
     */
    @Select("select l.*  from LATELETTER.LETTER_USER_RELATION lur, LATELETTER.LETTER l where lur.user_id = #{userId} AND lur.relation = 2 AND lur.letter_id = l.letter_id")
    List<Letter> queryUserReceiveLetter(@Param("userId") Long userId);

    /**
     * 删除用户单封收信
     * @param letterId
     * @param userId
     * @return
     */
    @Delete("delete from LETTER_USER_RELATION where letter_id = #{letterId} AND user_id=#{userId} AND relation=2")
    int deleteUserReceiveLetter(@Param("letterId") Long letterId, @Param("userId") Long userId);

    /**
     * 删除该封信的所有收信关系
     * @param letterId
     * @return
     */
    @Delete("delete from LETTER_USER_RELATION where letter_id = #{letterId} AND relation=2")
    int deleteAllReceiveLetter(@Param("letterId") Long letterId);

}
