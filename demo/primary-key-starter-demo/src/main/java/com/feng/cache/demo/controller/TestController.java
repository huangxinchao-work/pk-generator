package com.feng.cache.demo.controller;

import com.feng.cache.demo.entity.Person;
import com.feng.cache.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    PersonService personService;

    @PostMapping("/save")
    public Person put(@RequestBody Person person) {
        Person p = personService.save(person);
        return p;
    }

}