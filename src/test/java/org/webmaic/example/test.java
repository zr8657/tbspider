package org.webmaic.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.webmaic.example.util.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void redisTest(){

        redisUtil.setex("aaa","addd",100);

        System.out.println(redisUtil.get("aaa",0));
    }


}
