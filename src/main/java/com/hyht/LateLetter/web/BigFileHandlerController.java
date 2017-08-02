package com.hyht.LateLetter.web;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BFH")
public class BigFileHandlerController {
    @RequestMapping("/addPic")
    public String[] addPic(@RequestBody String[] picArray) throws Exception {

//        File file = new ClassPathResource("/picture/file_name.jpg").getFile();
        for (String str : picArray) System.out.println(str);

        return null;
    }
}
