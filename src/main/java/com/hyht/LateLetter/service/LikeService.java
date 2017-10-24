package com.hyht.LateLetter.service;

public interface LikeService {

    /**
     * 增加迟数的点赞数，同时添加用户—迟书点赞记录
     * @param letterId
     * @param userId
     * @return 0 表示失败， 1 表示成功
     */
    int addLetterLikeNum(Long letterId, Long userId);


    /**
     * 删除迟书时调用，同时删除点赞表记录、点赞用户关系
     * @param letterId
     * @return 0 表示失败， 1 表示成功
     */
    int deleteLetterLike(Long letterId);
}
