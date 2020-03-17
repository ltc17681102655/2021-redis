package com.ltc;

import com.ltc.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : ltc
 * @date : 2020/3/17 13:36
 * @description :
 */
@SpringBootTest
public class StringControllerTest {

    @Test
    public void demo1() {
        //缓存
        String KEY = "省份";
        String VALUE = "上海 云南 内蒙古 北京";
        RedisUtil.set(KEY, VALUE);
        System.out.println(RedisUtil.get(KEY));
    }

    @Test
    public void demo2() {
        //计数
        String KEY = "点赞";
        Integer VALUE = 1;
        RedisUtil.set(KEY, VALUE);
        RedisUtil.increment(KEY);
        System.out.println(RedisUtil.get(KEY));
    }

    @Test
    public void demo3() throws InterruptedException {
        //分布式锁
        String KEY = "省份";
        String VALUE = "上海 云南 内蒙古 北京";
        RedisUtil.set(KEY, VALUE, 5);
        if (!RedisUtil.hasKey(KEY)) {
            RedisUtil.set(KEY, "分布式锁");
        }
        System.out.println(RedisUtil.get(KEY));
        Thread.sleep(6000);
        if (!RedisUtil.hasKey(KEY)) {
            RedisUtil.set(KEY, "分布式锁");
            System.out.println(RedisUtil.get(KEY));
        }
    }
}
