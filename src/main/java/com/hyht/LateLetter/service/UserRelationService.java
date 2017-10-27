package com.hyht.LateLetter.service;

public interface UserRelationService {





    /**
     * 检测用户是否两者都没、关注、收信人、还是两者都有
     * @param userId 当前用户
     * @param otherUserId 受检测的用户
     * @return 0 表示两者都没有，1 表示关注，2 表示收信人, 3 两者都有
     */
    int checkUserRelation(Long userId, Long otherUserId);
}
