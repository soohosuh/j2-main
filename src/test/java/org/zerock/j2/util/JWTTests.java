package org.zerock.j2.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Log4j2
public class JWTTests {

    @Autowired
    private  JWTUtil jwtUtil;

    @Test
    public void testCreate(){

        Map<String, Object> claim = Map.of("email", "soohoseo@naver.com");

        String jwtStr = jwtUtil.generate(claim, 10);

        System.out.println(jwtStr);

    }

    @Test
    public void testToken(){

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InNvb2hvc2VvQG5hdmVyLmNvbSIsImlhdCI6MTY4OTc0NDM2OCwiZXhwIjoxNjg5NzQ0OTY4fQ.zPJkv_J58TCVMI2NaDrDpkTd2_pukuifLUtQOg_BwBA";

        try{
            jwtUtil.validateToken(token);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
