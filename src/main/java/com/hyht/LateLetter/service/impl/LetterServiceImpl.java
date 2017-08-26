package com.hyht.LateLetter.service.impl;


import com.hyht.LateLetter.dao.LetterDao;
import com.hyht.LateLetter.entity.Letter;
import com.hyht.LateLetter.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LetterServiceImpl implements LetterService{

    @Autowired
    LetterDao letterDao;

    @Override
    public Letter queryLetterById(int letterId) {
        return letterDao.queryLetterById(letterId);
    }
}
