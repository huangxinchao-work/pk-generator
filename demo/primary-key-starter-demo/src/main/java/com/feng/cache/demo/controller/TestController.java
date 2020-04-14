package com.feng.cache.demo.controller;

import com.feng.cache.demo.entity.Person;
import com.feng.cache.demo.service.PersonService;
import com.pk.generator.GainNextPk;
import com.pk.generator.StaticUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    PersonService personService;

//    @Autowired
//    InitBean initBean;

    @Autowired
    private GainNextPk gainNextPk;

    @PostMapping("/save")
    public Person put(@RequestBody Person person) {
//        long pk = gainNextPk.getPk();
//        System.out.println("pk :" + pk);
        long initializationCode = StaticUtils.getPk();
        System.out.println("pk :" + initializationCode);
        Person p = personService.save(person);
        return p;
    }

}