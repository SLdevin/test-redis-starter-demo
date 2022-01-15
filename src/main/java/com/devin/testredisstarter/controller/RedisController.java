package com.devin.testredisstarter.controller;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 52361
 * @Description
 * @create 2022-01-07 17:44
 */
@RestController
public class RedisController {

    @Resource
    RedissonClient redissonClient;

    @RequestMapping("lock")
    public String lock(){
        RLock stack = redissonClient.getLock("stack");
        stack.lock();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------------");
        stack.unlock();
        return  "lock";
    }

    @RequestMapping("shapping")
    public String shapping(){
        RBucket<Object> stack = redissonClient.getBucket("aa");
        Object o = stack.get();
        if (o!=null)
            return o.toString();
        return  "kong ";
    }
}
