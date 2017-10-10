package com.hyht.LateLetter.extraUtil;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class mkdirAndMkdirTest {


    @Test
    public void mkdirTest() throws Exception {
        //创建目录
        for (int j = 0; j < 2; j++) {
            String letterPath = "d:\\game\\bitchdir\\good" + j;
            File filePath = new File(letterPath);
            if (!filePath.exists() && !filePath.isDirectory()) {
                filePath.mkdir();
            }

        }
    }

    @Test
    public void mkdirsTest() throws Exception {
        //创建目录
        for (int j = 0; j < 2; j++) {
            String letterPath = "d:\\game\\bitchDir\\fuck" + j;
            File filePath = new File(letterPath);
            if (!filePath.exists() && !filePath.isDirectory()) {
                filePath.mkdirs();
            }
        }
    }
}
