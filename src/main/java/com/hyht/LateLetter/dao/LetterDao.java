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

    int insertLetter(Letter letter);

    @Delete("delete from LETTER where letter_id = #{letterId}")
    int deleteLetterById(@Param("letterId") long letterId);

    @Delete("delete from LETTER where user_id = #{userId}")
    int deleteLetterByUserId(@Param("userId") long userId);


    /**
     * 查找已公开的迟书，用于公共面板
     * @param publicFlag
     * @return
     */
    @Select("select * from letter where public_flag=#{publicFlag}")
    List<Letter> queryLettersByPublicFlag(@Param("publicFlag") int publicFlag);

}
