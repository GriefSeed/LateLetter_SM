package com.hyht.LateLetter.service.impl;

import com.hyht.LateLetter.dao.UsersDao;
import com.hyht.LateLetter.entity.Users;
import com.hyht.LateLetter.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersDao usersDao;

    /**
     * 是否已有用户，有就失败，没有就插入
     *
     * @param u
     * @return 0表示插入失败，1表示插入成功，2表示已有用户,result 表示影响行数
     */
    @Override
    public int regiter(Users u) throws Exception {
        Users uTemp = usersDao.queryUserByPhoneNum(u.getPhoneNum());
        if (uTemp != null) {
            return 2;
        } else {
            int result = usersDao.insertUser(u);
            if(result != 1){
                //insert error
                throw new Exception("insert error");
            }
            else {
                return result;
            }
        }
    }

/*
    public int transTest() {
        int result = usersDao.insertUser(new Users("2233", "666"));
        System.out.println(result + "      插入完成");
        try {
            throw new Exception("transactional test");
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }*/
}
