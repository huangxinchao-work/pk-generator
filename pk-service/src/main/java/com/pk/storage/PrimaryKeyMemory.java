package com.pk.storage;

import org.springframework.context.annotation.Configuration;

/**
 * @author: Roy
 * @description: 存储生成后的主键
 * @create: 2020-04-14 17:07
 * @since: JDK1.8
 */
@Configuration
public class PrimaryKeyMemory {

    /**
     * 存储生成的主键，便于系统重启或down掉之后继续上一次的编码增加
     * 目前可以对基于此starter的项目提供三种方式的存储支持：
     * 1、如果有redis的话，会基于redisTemplate进行存入到redis的操作
     * 2、项目正常关闭的话，在destroy时读取该项目的jdbc配置存到数据库中（待完善）
     * 3、用spring自带的jdbc存到数据库中（待完善）
     */






}
