package com.hyht.LateLetter.dao;

import com.hyht.LateLetter.entity.Test;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDao {

    @Select("select * from test where name=#{name}")
    Test queryTestByName(@Param("name") String name);

}
