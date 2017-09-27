package com.hyht.LateLetter.web;


import com.hyht.LateLetter.dao.UsersDao;
import com.hyht.LateLetter.dto.ObjWithMsg;
import com.hyht.LateLetter.entity.Users;
import com.hyht.LateLetter.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {


    @Autowired
    UsersDao usersDao;

    @Autowired
    UsersService usersService;


    private final static Logger logger = LoggerFactory.getLogger(MainController.class);

    /**
     * 登陆
     *
     * @param u
     * @return
     */
    @RequestMapping("/login")
    public Object login(@RequestBody Users u) {
        Users users = usersDao.queryUserByPhoneNum(u.getPhoneNum());

        if (users != null) {
            if (u.getUserPassword().equals(users.getUserPassword())) {
                return new ObjWithMsg(users, "T", "SUCCESS");
            } else
                return new ObjWithMsg(users, "F", "PASSWORD_ERROR");
        }
        return new ObjWithMsg(null, "F", "USER_NO_EXIST");
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestBody Users u) {
        Users userTemp = new Users(u.getUserPassword(), u.getPhoneNum());
        //首次用户注册，手机号码即用户名
        userTemp.setNickname(userTemp.getPhoneNum());
        int result;
        try {
            result = usersService.regiter(userTemp);
        } catch (Exception e) {
            e.printStackTrace();
            return new ObjWithMsg(null, "F", "REGISTER_ERROR");
        }
        if (result == 1) {
            return new ObjWithMsg(userTemp, "T", "SUCCESS");
        } else {
            return new ObjWithMsg(null, "F", "PHONE_EXISTED");
        }

    }


    @RequestMapping(value = "/test")
    public String test() throws Exception {
        System.out.println("test");
        return "test success";
    }

    @RequestMapping(value = "/jsonTest")
    public Object jsonTest(@RequestBody Users u) throws Exception {
        System.out.println("jsonTest");
        return u;
    }

}
