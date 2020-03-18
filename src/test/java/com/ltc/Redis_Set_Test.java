package com.ltc;

import com.ltc.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : ltc
 * @date : 2020/3/17 20:09
 * @description :
 */
@SpringBootTest
public class Redis_Set_Test {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void demo() {
        String KEY = "SET";
        redisUtils.sSet(KEY, "A");
        redisUtils.sSet(KEY, "B");
        redisUtils.sSet(KEY, "C");
        redisUtils.sSet(KEY, "D");
        long a = redisUtils.sSet(KEY, "A");
        System.out.println(a);
        //
        System.out.println(redisUtils.sGet(KEY));
    }


}
