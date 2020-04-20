package com.pk.storage;

import com.pk.generator.InitBean;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author: Roy
 * @description: 将主键保存到redis的操作
 * @create: 2020-04-14 17:23
 * @since: JDK1.8
 */
public class RedisStorage {

    public static final String REDIS_KEY = "primaryKey";

    private InitBean initBean;
    private RedisTemplate redisTemplate;

    public RedisStorage(InitBean initBean, RedisProperties redisProperties, RedisTemplate redisTemplate){
        this.initBean = initBean;
        this.redisTemplate = redisTemplate;
    }

    public void storage(){
        //从initBean中获取当前主键
        long storageCode = initBean.getStorageCode();
        //将当前主键存到redis
        redisTemplate.opsForValue().set(REDIS_KEY, storageCode);
    }

}
