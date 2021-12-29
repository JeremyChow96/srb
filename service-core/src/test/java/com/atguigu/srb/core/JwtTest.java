package com.atguigu.srb.core;


import com.atguigu.srb.base.util.JwtUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTest {

    @Test
    public void JwtTokenTest(){
        //生成token
        String token = JwtUtils.createToken(7l,"15380230735");
        Long userId = JwtUtils.getUserId(token);

    }
}
