package com.hyht.LateLetter.web;


import com.hyht.LateLetter.dao.UsersDao;
import com.hyht.LateLetter.dto.ObjWithMsg;
import com.hyht.LateLetter.entity.Users;
import com.hyht.LateLetter.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    UsersDao usersDao;

    @Autowired
    UsersService usersService;


    /**
     * 登陆
     * @param u
     * @return
     */
    @RequestMapping("/login")
    public Object login(@RequestBody Users u) {
        Users users = usersDao.queryUserByPhoneNum(u.getPhoneNum());

        if (users != null) {
            if(u.getUserPassword().equals(users.getUserPassword())){
                return new ObjWithMsg(users, "T", "SUCCESS");
            }
            else
                return new ObjWithMsg(users, "F", "PASSWORD_ERROR");
        }
        return new ObjWithMsg(null, "F", "USER_NO_EXIST");
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping("/register")
    public Object register(String pw, String phoneNum){
        Users userTemp = new Users(pw, phoneNum);
        //首次用户注册，手机号码即用户名
        userTemp.setNickname(phoneNum);
        int result;
        try {
            result = usersService.regiter(userTemp);
        }catch (Exception e){
            e.printStackTrace();
            return new ObjWithMsg(null, "F", "REGISTER_ERROR");
        }
        if(result == 1){
            return new ObjWithMsg(userTemp, "T", "SUCCESS");
        }
        else {
            return new ObjWithMsg(null, "F", "PHONE_EXISTED");
        }

    }


}
