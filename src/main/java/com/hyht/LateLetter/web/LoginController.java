package com.hyht.LateLetter.web;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/")
    public String loginPage() {
        return "test sucdess~您好，欢迎来到Spring boot";
    }

}
