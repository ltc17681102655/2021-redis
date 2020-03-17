package com.ltc.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: ltc
 * @Date: 2020/3/17 18:19
 * @Description: 缓存工具类
 */
public class RedisUtil {
    private RedisUtil() {
    }

    private static RedisTemplate instance;

    public static RedisTemplate getInstance() {
        if (instance == null) {
            synchronized (RedisTemplate.class) {
                instance = (RedisTemplate) SpringUtil.getBean("apiRedisTemplate");
            }
        }
        return instance;
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @return true=有；false=没有
     */
    public static boolean hasKey(final String key) {
        return getInstance().hasKey(key);
    }


    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public static boolean expire(final String key, final long timeout) {

        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public static boolean expire(final String key, final long timeout, final TimeUnit unit) {

        Boolean ret = getInstance().expire(key, timeout, unit);
        return ret != null && ret;
    }

    /**
     * 删除单个key
     *
     * @param key 键
     */
    public static void del(final String key) {
        getInstance().delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys 键集合
     */
    public static void del(final Collection<String> keys) {
        getInstance().delete(keys);
    }

    /**
     * 存入普通对象
     *
     * @param key     键
     * @param value   值
     * @param timeout 有效期，单位秒
     */
    public static void set(final String key, final Object value, final long timeout, final TimeUnit timeUnit) {
        getInstance().opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 存入普通对象
     *
     * @param key     键
     * @param value   值
     * @param timeout 有效期，单位秒
     */
    public static void set(final String key, final Object value, final long timeout) {
        set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 存入普通对象
     *
     * @param key   键
     * @param value 值
     */
    public static void set(final String key, final Object value) {
        getInstance().opsForValue().set(key, value);
    }

    //String

    /**
     * 递增
     *
     * @param key 键
     */
    public static void increment(final String key) {
        getInstance().opsForValue().increment(key);
    }

    /**
     * 递减
     *
     * @param key 键
     */
    public static void decrement(final String key) {
        getInstance().opsForValue().decrement(key);
    }

    /**
     * 获取普通对象
     *
     * @param key 键
     * @return 对象
     */
    public static Object get(final String key) {
        return getInstance().opsForValue().get(key);
    }

    /**
     * 获取普通对象
     *
     * @param key 键
     * @return 对象
     */
    public static <T> T get(final String key, Class<T> tClass) {
        return (T) getInstance().opsForValue().get(key);
    }

    // 存储Hash操作

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public static void hPut(final String key, final String hKey, final Object value) {

        getInstance().opsForHash().put(key, hKey, value);
    }

    /**
     * 往Hash中存入多个数据
     *
     * @param key    Redis键
     * @param values Hash键值对
     */
    public static void hPutAll(final String key, final Map<String, Object> values) {
        getInstance().opsForHash().putAll(key, values);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public static Object hGet(final String key, final String hKey) {

        return getInstance().opsForHash().get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public static List<Object> hMultiGet(final String key, final Collection<Object> hKeys) {

        return getInstance().opsForHash().multiGet(key, hKeys);
    }

    // 存储Set相关操作

    /**
     * 往Set中存入数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 存入的个数
     */
    public static long sSet(final String key, final Object... values) {
        Long count = getInstance().opsForSet().add(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 删除Set中的数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 移除的个数
     */
    public static long sDel(final String key, final Object... values) {
        Long count = getInstance().opsForSet().remove(key, values);
        return count == null ? 0 : count;
    }

    // 存储List相关操作

    /**
     * 往List中存入数据
     *
     * @param key   Redis键
     * @param value 数据
     * @return 存入的个数
     */
    public static long lPush(final String key, final Object value) {
        Long count = getInstance().opsForList().rightPush(key, value);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    public static long lPushAll(final String key, final Collection<Object> values) {
        Long count = getInstance().opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    public static long lPushAll(final String key, final Object... values) {
        Long count = getInstance().opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 从List中获取begin到end之间的元素
     *
     * @param key   Redis键
     * @param start 开始位置
     * @param end   结束位置（start=0，end=-1表示获取全部元素）
     * @return List对象
     */
    public static List<Object> lGet(final String key, final int start, final int end) {
        return getInstance().opsForList().range(key, start, end);
    }

    //list相关操作

    public static void listLeftPush(String k, List<Object> list, long time, TimeUnit timeUnit) {
        getInstance().opsForList().leftPush(k, list);
        getInstance().expire(k, time, timeUnit);
    }

    public static void listLeftPush(String k, List<Object> list) {
        getInstance().opsForList().leftPush(k, list);
    }

    public static void listLeftPop(String k, long time, TimeUnit timeUnit) {
        getInstance().opsForList().leftPop(k, time, timeUnit);
        getInstance().expire(k, time, timeUnit);
    }

    public static void listLeftPop(String k) {
        getInstance().opsForList().leftPop(k);
    }

    public static void listRightPush(String k, List<Object> list, long time, TimeUnit timeUnit) {
        getInstance().opsForList().rightPush(k, list);
        getInstance().expire(k, time, timeUnit);
    }

    public static void listRightPush(String k, List<Object> list) {
        getInstance().opsForList().rightPush(k, list);
    }

    public static void listRightPop(String k, long time, TimeUnit timeUnit) {
        getInstance().opsForList().rightPop(k, time, timeUnit);
        getInstance().expire(k, time, timeUnit);
    }

    public static void listRightPop(String k) {
        getInstance().opsForList().rightPop(k);
    }

    /**
     * @param key Redis键
     * @return List对象
     */
    public static <T> List<T> getList(final String key, Class<T> clazz) {
        long size = getInstance().opsForList().size(key);
        return (List<T>) lGet(key, 0, (int) size);
    }


}
