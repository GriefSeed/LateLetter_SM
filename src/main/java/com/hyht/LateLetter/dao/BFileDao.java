package com.hyht.LateLetter.dao;

import com.hyht.LateLetter.entity.BFile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BFileDao {

    int insertBFile(BFile bfile);

    @Select("select * from BFILE where letter_id = #{letterId}")
    List<BFile> querySingleLetterFiles(long letterId);

    @Delete("delete from BFILE where letter_id = #{letterId}")
    int deleteBfileByLetterId(long letterId);
}
