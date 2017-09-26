package com.hyht.LateLetter.dao;


import com.hyht.LateLetter.entity.BFile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BFileDaoTest {

    @Autowired
    BFileDao bFileDao;

    List<BFile> bFileList;

    @Test
    public void deleteListTest() throws Exception {
    }

    @Test
    public void insertListTest() throws Exception {
        bFileList = new ArrayList<BFile>();
        bFileList.add(new BFile(1, "http://", 1));
        bFileList.add(new BFile(1, "http://", 1));
        bFileList.add(new BFile(1, "http://", 1));
        bFileList.add(new BFile(1, "http://", 2));
        bFileList.add(new BFile(1, "http://", 3));
        /*bFileDao.insertBFileList(bFileList);*/
    }


    @Before
    public void setUp() throws Exception {

    }
}
