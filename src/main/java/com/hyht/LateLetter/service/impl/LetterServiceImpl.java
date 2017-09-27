package com.hyht.LateLetter.service.impl;


import com.hyht.LateLetter.dao.LetterDao;
import com.hyht.LateLetter.dao.UsersDao;
import com.hyht.LateLetter.entity.Letter;
import com.hyht.LateLetter.entity.Users;
import com.hyht.LateLetter.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class LetterServiceImpl implements LetterService{

    @Autowired
    LetterDao letterDao;

    @Autowired
    UsersDao usersDao;

    @Override
    public Letter queryLetterById(int letterId) {
        return letterDao.queryLetterById(letterId);
    }

    //未完成
    @Override
    public int insertLetter(Users u, Letter l) {
        //spend user time
        Integer restTimeBefore = Integer.parseInt(usersDao.queryUserRestTimeById(u.getUserId()));

        double day = (l.getDeadline().getTime() - l.getStartDate().getTime())/(1000*3600*24);
        Integer costDay = (int)Math.ceil(day);
        if(costDay > restTimeBefore) {
            return 0;
        }else {
            //更新用户时间
            //插入迟书
            int result = letterDao.insertLetter(l);
            return 1;
        }

    }

    @Override
    public int deleteLetter(Users u, Letter l) {
        return 0;
    }

    @Override
    public List<Letter> queryAllPublicLetter() {
        return null;
    }
}
