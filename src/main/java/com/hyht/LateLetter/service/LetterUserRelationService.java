package com.hyht.LateLetter.service;

public interface LetterUserRelationService {

    /**
     * 批量删除用户的收藏
     * @return
     */
    int deleteUserCollectionList(Long userId, Long[] letterIdList);


}
