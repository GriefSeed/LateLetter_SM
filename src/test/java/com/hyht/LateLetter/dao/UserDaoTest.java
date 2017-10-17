package com.hyht.LateLetter.dao;

import com.hyht.LateLetter.entity.Users;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTest {

    @Autowired
    UsersDao usersDao;

    Users u;

    @Test
    public void insertUserTest() throws Exception {
        usersDao.insertUser(u);

    }

    @Test
    public void queryUserByIdTest()throws Exception{
        System.out.println(usersDao.queryUserById(10008).toString());
    }

    @Before
    public void setUp() throws Exception {
        //u = new Users("css", "css", 1, "http://www.baidu.com", "30000", "18814128750", "张大大", "4412035548313", 0, 1);
    }
}
