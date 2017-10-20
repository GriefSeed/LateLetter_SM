package com.hyht.LateLetter.service.impl;

import com.hyht.LateLetter.dao.LetterUserRelationDao;
import com.hyht.LateLetter.service.LetterUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LetterUserRelationServiceImpl implements LetterUserRelationService {

    @Autowired
    LetterUserRelationDao letterUserRelationDao;



    @Override
    public int deleteUserCollectionList(Long userId, Long[] letterIdList) {
        for(Long letterId : letterIdList){
            letterUserRelationDao.deleteLetterUserCollection(letterId, userId);
        }
        return 1;
    }


}
