package com.hyht.LateLetter.dao;

import com.hyht.LateLetter.entity.Letter;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LetterDao {
    @Select("select * from letter where letter_id=#{letterId}")
    Letter queryLetterById(@Param("letterId") long letterId);

    @Select("select * from letter where user_id=#{userId}")
    List<Letter> queryLetterByUserId(@Param("userId") long userId);

    int insertLetter(Letter letter);

    @Delete("delete from LETTER where letter_id = #{letterId}")
    int deleteLetterById(@Param("letterId") long letterId);

    @Delete("delete from LETTER where user_id = #{userId}")
    int deleteLetterByUserId(@Param("userId") long userId);


    /**
     * 查找公开并已到期的迟书，用于公共面板
     * @return
     */
    @Select("select * from LETTER WHERE deadline <= sysdate AND public_flag = 1")
    List<Letter> queryPublicLetterAndBefore();

    /**
     * 查公开并未到期的迟书，用于公共面板
     * @return
     */
    @Select("select * from LETTER WHERE deadline > sysdate AND public_flag = 1")
    List<Letter> queryPublicLetterAndAfter();

}
