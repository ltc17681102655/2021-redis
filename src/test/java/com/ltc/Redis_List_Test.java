package com.ltc;

import com.ltc.utils.RedisUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author : ltc
 * @date : 2020/3/17 19:34
 * @description :
 */
@SpringBootTest
public class Redis_List_Test {

    private static List<Object> list;
    private static List<Object> list2;

    static {
        list = Lists.newArrayList("北京", "上海", "广州", "深圳");
        list2 = Lists.newArrayList("北京", "上海", "广州", "深圳");
    }

    //缓存
    @Test
    public void demo() {
        String KEY = "AKC456";
//        RedisUtil.listLeftPush(KEY, list2);
        RedisUtil.lPush(KEY, "A");
        RedisUtil.lPush(KEY, "B");
        RedisUtil.lPush(KEY, "A");
        System.out.println(RedisUtil.getList(KEY, List.class));
    }

    @Test
    public void demo2() {
        //栈:先进后出:lpush lpop
        String KEY = "栈";
        RedisUtil.listLeftPush(KEY, Lists.newArrayList("A"));
        RedisUtil.listLeftPush(KEY, Lists.newArrayList("B"));
        RedisUtil.listLeftPush(KEY, Lists.newArrayList("C"));
        RedisUtil.listLeftPop(KEY);
        //[[B], [A]]
        System.out.println(RedisUtil.getList(KEY, List.class));
    }

    @Test
    public void demo3() {
        //队列:先进先出:lpush rpop
        String KEY = "队列";
        RedisUtil.listLeftPush(KEY, Lists.newArrayList("A"));
        RedisUtil.listLeftPush(KEY, Lists.newArrayList("B"));
        RedisUtil.listLeftPush(KEY, Lists.newArrayList("C"));
        //C B A
        RedisUtil.listRightPop(KEY);
        //[[C], [B]]
        System.out.println(RedisUtil.getList(KEY, List.class));
    }
}
