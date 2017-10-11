package com.hyht.LateLetter.web;


import com.hyht.LateLetter.entity.Letter;
import com.hyht.LateLetter.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/PAGE")
@Controller
public class WebPageController {


    @Autowired
    LetterService letterService;

    @RequestMapping("/shareLetter/{letterId}")
    public String shareLetter(@PathVariable("letterId") String letterId, Model model) {
        //return letterService.queryLetterById(Integer.parseInt(letterId)).toString() + " banana~";
        //System.out.println(letterService.queryLetterById(letterId));
        Letter letter = letterService.queryLetterById(Integer.parseInt(letterId));
        model.addAttribute("letter", letter);
        //model.addAttribute("letter_content", letterId + " bitch2233");

        return "show_letter";
    }


}
