package com.hyht.LateLetter.web;


import com.hyht.LateLetter.EnvirArgs;
import com.hyht.LateLetter.dao.BFileDao;
import com.hyht.LateLetter.entity.BFile;
import com.hyht.LateLetter.entity.Letter;
import com.hyht.LateLetter.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/PAGE")
@Controller
public class WebPageController {


    @Autowired
    LetterService letterService;

    @Autowired
    BFileDao bFileDao;

    @RequestMapping("/shareLetter/{letterId}")
    public String shareLetter(@PathVariable("letterId") String letterId, Model model) {
        //return letterService.queryLetterById(Integer.parseInt(letterId)).toString() + " banana~";
        //System.out.println(letterService.queryLetterById(letterId));
        Letter letter = letterService.queryLetterById(Integer.parseInt(letterId));
        if (letter != null) {
            List<BFile> fileList = bFileDao.querySingleLetterFiles(Integer.valueOf(letterId));
            Map files;
            if(!fileList.isEmpty()){
                files = new HashMap<String, Integer>();
                for (BFile bFile : fileList) {
                    files.put(EnvirArgs.internetFileUrl + bFile.getFileUrl().replaceAll("\\\\","/"), bFile.getFileType());
                }
            }
            else {
                files = null;
            }
            model.addAttribute("files", files);
        }
        model.addAttribute("letter", letter);

        //model.addAttribute("letter_content", letterId + " bitch2233");

        return "show_letter";
    }


}
