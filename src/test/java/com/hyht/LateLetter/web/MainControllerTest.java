package com.hyht.LateLetter.web;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MainControllerTest {
    @Test
    public void querySingleLetter() throws Exception {
        String infra = "{\"letterId\":10011,\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/json;UTF-8"));
        HttpEntity<String> strEntity = new HttpEntity<String>(infra,headers);

        RestTemplate restTemplate = new RestTemplate();
        JSONObject jo2 = restTemplate.postForObject("http://192.168.0.104:8081/querySingleLetter",strEntity,JSONObject.class);
        System.out.println(jo2.toString());
    }

}