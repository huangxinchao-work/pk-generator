package com.feng.cache.demo.service.impl;

import com.feng.cache.annotation.CacheEvict;
import com.feng.cache.annotation.Cacheable;
import com.feng.cache.annotation.CaffeineCache;
import com.feng.cache.annotation.RedisCache;
import com.feng.cache.demo.entity.Person;
import com.feng.cache.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PersonServiceImpl implements PersonService {
    Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public Person save(Person person) {
        //TODO 等待stater
        return person;
    }

}
