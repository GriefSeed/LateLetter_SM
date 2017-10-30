package com.hyht.LateLetter.service.impl;


import com.hyht.LateLetter.dao.LetterDao;
import com.hyht.LateLetter.dao.UsersDao;
import com.hyht.LateLetter.entity.Letter;
import com.hyht.LateLetter.entity.Users;
import com.hyht.LateLetter.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class LetterServiceImpl implements LetterService {

    @Autowired
    LetterDao letterDao;

    @Autowired
    UsersDao usersDao;

    @Override
    public Letter queryLetterById(int letterId) {
        return letterDao.queryLetterById(letterId);
    }


    @Override
    public long insertLetter(Letter l) {
        //spend user time
        Integer restTimeBefore = Integer.parseInt(usersDao.queryUserRestTimeById(l.getUserId()));

        double day = (l.getDeadline().getTime() - l.getStartDate().getTime()) / (1000 * 3600 * 24);
        //不足一天算一天
        Integer costDay = (int) Math.ceil(day);
        //转换为秒，如果不能被60 * 60 * 24 整除，即秒数不为0, 那么就 + 1
        double surplus = ((l.getDeadline().getTime() - l.getStartDate().getTime())/1000) % (24 * 60 * 60);
        if(surplus > 0){
            costDay = costDay + 1;
        }
        //时间不够的，插入失败
        if (costDay > restTimeBefore) {
            return 0;
        } else {
            //更新用户时间
            String restTimeAfter = String.valueOf(restTimeBefore - costDay);
            usersDao.updateUserRestTime(restTimeAfter, l.getUserId());
            //插入迟书
            letterDao.insertLetter(l);
            return l.getLetterId();
        }

    }

    /**
     * 批量查询迟书集合
     *
     * @param letterIdList
     * @return
     */
    @Override
    public List<Letter> queryLetterList(Long[] letterIdList) {
        List<Letter> letters = new ArrayList<Letter>();
        for (Long letterId : letterIdList) {
            letters.add(letterDao.queryLetterById(letterId));
        }
        return letters;
    }



    @Override
    public int deleteLetter(Long letterId, Long userId) {
        int result = 0;
        //先判断用户时间是否足够
        Users uTemp = usersDao.queryUserById(userId);
        Letter l = letterDao.queryLetterById(letterId);
        double day = (l.getDeadline().getTime() - l.getStartDate().getTime()) / (1000 * 3600 * 24);
        //不足一天算一天
        Integer costDay = (int) Math.ceil(day);
        //转换为秒，如果不能被60 * 60 * 24 整除，即秒数不为0 那么就 + 1
        double surplus = ((l.getDeadline().getTime() - l.getStartDate().getTime())/1000) % (24 * 60 * 60);
        if(surplus > 0){
            costDay = costDay + 1;
        }
        Integer userRestTime = Integer.valueOf(uTemp.getRestTime());
        //当用户时间大于或等于该封迟书的封存时间时，才允许删除
        if (userRestTime >= costDay) {
            //等价扣除相同时间
            usersDao.updateUserRestTime(String.valueOf(userRestTime - costDay), userId);
            result = letterDao.deleteLetterById(letterId);
        }
        return result;
    }

    @Override
    public List<Letter> queryAllPublicLetter() {
        return null;
    }
}
