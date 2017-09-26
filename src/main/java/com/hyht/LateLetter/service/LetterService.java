package com.hyht.LateLetter.service;

import com.hyht.LateLetter.entity.Letter;
import com.hyht.LateLetter.entity.Users;

import java.util.List;


public interface LetterService {
    Letter queryLetterById(int letterId);


    /**
     * 创建迟书，创建时扣除时间、以天为单位
     * @param u
     * @param l
     * @return
     */
    int insertLetter(Users u, Letter l);

    /**
     * 删除迟书，删除时扣除跟创建时一样的时间
     * @return
     */
    int deleteLetter(Users u, Letter l);


    List<Letter> queryAllPublicLetter();
}
