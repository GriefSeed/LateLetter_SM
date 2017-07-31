package com.hyht.LateLetter.web;



import com.hyht.LateLetter.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private TestService testService;

    @RequestMapping("/")
    public String loginPage() {
        return "<h1>TEST SUCCESS！迟书后台连通测试</h1>"+ "</br>"+ testService.queryTestByName("bitch").getAge();
    }

}
