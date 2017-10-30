package com.hyht.LateLetter.extraUtil;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class StringTest {

    @Test
    public void stringFormat() throws Exception {
        String s = String.format("hello%swhos%syour%s", 123, 459, "daddy");

        String s1 = MessageFormat.format("hello{0}whos{1}your{2}", 123, 459, "daddy");

        System.out.println(s);

    }

    @Test
    public void stringLength() throws Exception {
        String[] s1 = null;

        String[] s2 = new String[]{"1"};

        System.out.println(s1 + "       " + s2.length);

    }

    @Test
    public void timeTest() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time1 = dateFormat.parse("2013-11-11 22:00:00");
        Date time2 = dateFormat.parse("2013-11-11 22:00:00");
        double day = (time2.getTime() - time1.getTime())/(1000 * 60 * 60 * 24);
        //不足一天算一天
        Integer costDay = (int) Math.ceil(day);
        //转换为秒，如果不能被60 * 60 * 24 整除，那么就 + 1
        double surplus = ((time2.getTime() - time1.getTime())/1000) % (24 * 60 * 60);
        System.out.println(surplus + " hhh ");
        if(surplus > 0){
            costDay = costDay + 1;
        }
        System.out.println(time1.getTime() +"  ======    "+time2.getTime() + "  ====   " + day + "   =====      " + costDay);
    }

}
