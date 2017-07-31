package com.hyht.LateLetter.service.impl;

import com.hyht.LateLetter.dao.TestDao;
import com.hyht.LateLetter.entity.Test;
import com.hyht.LateLetter.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestDao testDao;

    @Override
    public Test queryTestByName(String name) {
        return testDao.queryTestByName(name);
    }
}
