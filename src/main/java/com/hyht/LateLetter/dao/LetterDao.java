package com.hyht.LateLetter.dao;

import org.apache.ibatis.annotations.Param;
import com.hyht.LateLetter.entity.Letter;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterDao {
    @Select("select * from letter where letter_id=#{letterId}")
    Letter queryLetterById(@Param("letterId") int letterId);
}
