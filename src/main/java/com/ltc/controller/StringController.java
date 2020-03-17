package com.ltc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : ltc
 * @date : 2020/3/17 13:36
 * @description :
 */
@RestController
public class StringController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("set")
    public String set() {
        redisTemplate.opsForValue().set("A", "B");
        return "SUCCESS";
    }

    @GetMapping("get")
    public String get() {
        String value = (String) redisTemplate.opsForValue().get("A");
        return value;
    }

}
