package com.hyht.LateLetter.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface CountDao {


    /**
     * 直接收件人数量
     * @return
     */
    @Select("select count(*) from LATELETTER.USER_RELATION ur where ur.master_user_id = #{userId} AND ur.relation = 2")
    int queryUserReceivePeopleNum(@Param("userId") Long userId);

    /**
     * 我的关注数量
     * @return
     */
    @Select("select count(*) from LATELETTER.USER_RELATION ur where ur.master_user_id = #{userId} AND  ur.relation = 1")
    int queryUserAttentionNum(@Param("userId") Long userId);

    /**
     * 已写迟书数量
     * @return
     */
    @Select("select count(*) from LATELETTER.LETTER l where l.user_id = #{userId}")
    int queryUserLetterNum(@Param("userId") Long userId);

    /**
     * 收到迟书数量，没有未到期，不可知的选项
     * @return
     */
    @Select("select count(*) from LATELETTER.LETTER_USER_RELATION lur, LATELETTER.LETTER l where lur.user_id = #{userId} AND lur.relation = 2 AND lur.letter_id = l.letter_id AND NOT (l.count_down = 0 AND l.deadline > sysdate)")
    int queryUserReceiveLetterNum(@Param("userId") Long userId);

    /**
     * 我的收藏数量
     * @return
     */
    @Select("select count(*) from LATELETTER.LETTER_USER_RELATION lur where lur.user_id = #{userId} AND lur.relation = 1")
    int queryUserCollectionNum(@Param("userId") Long userId);
}
