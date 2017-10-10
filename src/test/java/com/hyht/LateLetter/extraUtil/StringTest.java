package com.hyht.LateLetter.extraUtil;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.MessageFormat;

@SpringBootTest
public class StringTest
{

    @Test
    public void stringFormat() throws Exception {
        String s = String.format("hello%swhos%syour%s", 123, 459, "daddy");

        String s1 = MessageFormat.format("hello{0}whos{1}your{2}", 123, 459, "daddy");

        System.out.println(s);

    }
}
