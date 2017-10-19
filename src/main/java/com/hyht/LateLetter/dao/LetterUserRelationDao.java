package com.hyht.LateLetter.dao;

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
     * 查询用户收藏集，返还给前端
     * @param userId
     * @return
     */
    @Select("select LETTER_USER_RELATION.letter_id from LETTER_USER_RELATION where user_id=#{userId} AND relation=1")
    List<Long> queryCollectionById(@Param("userId") Long userId);

}
