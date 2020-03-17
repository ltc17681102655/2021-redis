package com.ltc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void chinese() {
        redisTemplate.opsForValue().set("AA", "中国");
        System.out.println(redisTemplate.opsForValue().get("AA"));
    }

}
