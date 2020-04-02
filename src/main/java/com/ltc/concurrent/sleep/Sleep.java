package com.ltc.concurrent.sleep;

/**
 * @author : ltc
 * @date : 2020/4/2 13:38
 * @description :
 */

public class Sleep {
//sleep方法是不会释放当前的锁的，而wait方法会
//    它们还有这些区别：
//    wait可以指定时间，也可以不指定；而sleep必须指定时间。
//    wait释放cpu资源，同时释放锁；sleep释放cpu资源，但是不释放锁，所以易死锁。
//    wait必须放在同步块或同步方法中，而sleep可以再任意位置
}
