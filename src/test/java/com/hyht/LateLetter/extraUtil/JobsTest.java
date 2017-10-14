package com.hyht.LateLetter.extraUtil;

import com.hyht.LateLetter.EnvirArgs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JobsTest {

    @Test
    public void cleanCheckImg(){
        String dirStr = EnvirArgs.extraFilePath + "\\checkImg";
        File file = new File(dirStr);
        File[] files = file.listFiles();
        if(files.length > 0){
            for(File temp : files){
                if(temp.isFile()){
                    temp.delete();
                }
            }
            System.out.println("bitch i wil fuck you ");
        }
        else{
            System.out.println("nothing was found ");
        }
    }
}
