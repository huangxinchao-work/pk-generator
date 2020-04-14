package com.feng.cache.demo.service.impl;

import com.feng.cache.demo.entity.Person;
import com.feng.cache.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public Person save(Person person) {
        //TODO 等待stater
        return person;
    }

}
