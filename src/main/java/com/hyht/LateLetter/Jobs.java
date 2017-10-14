package com.hyht.LateLetter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Jobs {

    private final static Logger logger = LoggerFactory.getLogger(Jobs.class);

    /**
     * 每天凌晨3点，清除验证码图片
     */
    @Scheduled(cron="0 0 3 * * ?")
    public static void cleanCheckImg(){
        String dirStr = EnvirArgs.extraFilePath + "\\checkImg";
        File file = new File(dirStr);
        File[] files = file.listFiles();
        if(files.length > 0){
            for(File temp : files){
                if(temp.isFile()){
                    temp.delete();
                }
            }
            logger.info("+++++++++++++++++++++++++++++++++++++++++++++++已清除验证码缓存+++++++++++++++++++++++++++++++++++++++++++++++");
        }
        else{
            logger.info("++++++++++++++++++++++++++++++++++++++++++验证码文件夹内并没有验证码图片+++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }
}
