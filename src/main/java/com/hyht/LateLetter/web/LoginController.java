package com.hyht.LateLetter.web;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/d")
    public String loginPageTest() {
        System.out.println("热部署2333333");
        System.out.println("热部署2333333");
        return "<h1>TEST SUCCESS！迟书后台连通测试</h1>";
    }

}
