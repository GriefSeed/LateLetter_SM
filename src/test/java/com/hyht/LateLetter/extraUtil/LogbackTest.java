package com.hyht.LateLetter.extraUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LogbackTest {
    private final static Logger logger = LoggerFactory.getLogger(LogbackTest.class);


    @Test
    public void configFileTest() throws Exception {
        logger.debug("debug>>>>>>>>>>>>>>>");
        logger.info("INFO>>>>>>>>>>>>>>");
        logger.warn("warn>>>>>>>>>>>>>>>>>");
        logger.error("error>>>>>>>>>>>>>>");
    }
}
