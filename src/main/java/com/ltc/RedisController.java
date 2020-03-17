package com.ltc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ltc
 * @date : 2020/3/17 13:33
 * @description :
 */
@RestController
public class RedisController {

    @GetMapping("test")
    public String test() {

        return "SUCCESS";
    }
}
