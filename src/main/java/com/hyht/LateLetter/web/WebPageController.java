package com.hyht.LateLetter.web;


import com.hyht.LateLetter.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/PAGE")
@RestController
public class WebPageController {


    @Autowired
    LetterService letterService;

    @RequestMapping("/shareLetter/{letterId}")
    public String shareLetter(@PathVariable("letterId") String letterId) {
        return letterService.queryLetterById(Integer.parseInt(letterId)).toString() + " banana~";
        //System.out.println(letterService.queryLetterById(letterId));
    }
}
