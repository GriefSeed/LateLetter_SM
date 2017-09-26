package com.hyht.LateLetter.service.impl;

import com.hyht.LateLetter.entity.Users;
import com.hyht.LateLetter.service.UsersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersServiceImplTest {
    Users u;

    @Autowired
    UsersService usersService;

    @Test
    public void regiter() throws Exception {
        int result = usersService.regiter(u);
        System.out.println(result);
    }

    @Test
    public void transTest() throws Exception {

        /*int result = usersService.transTest();
        System.out.println(result);*/


    }


    @Before
    public void setUp() throws Exception {
        u = new Users("123", "18813");
    }
}