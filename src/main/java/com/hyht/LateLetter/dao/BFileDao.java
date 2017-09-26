package com.hyht.LateLetter.dao;

import com.hyht.LateLetter.entity.BFile;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

@Repository
public interface BFileDao {

    int insertBFile(BFile bfile);

    @Delete("delete from BFILE where letter_id = #{letterId}")
    int deleteBfileByLetterId(long letterId);
}
