package com.feng.cache.demo.service.impl;

import com.feng.cache.demo.entity.Person;
import com.feng.cache.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Properties;

@Service
public class PersonServiceImpl implements PersonService {
    Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public Person save(Person person) {
        //TODO 等待stater
        return person;
    }

    //测试Properties的使用方法
    public static void main(String[] args) {
        //构建一个空的Properties对象
        Properties properties = new Properties();
        //向创建好的Properties对象中赋值
        properties.put("redis.host", "moby.mall.service.com");
        properties.put("redis.port", "6708");
        properties.put("redis.password", "123456789");
        //创建一个Properties文件
//        createPropertiesFile();
//        readProperties();

    }

    public static void createPropertiesFile(String fileName, Properties properties){
        //创建一个Properties文件
        File propertiesFile = new File(fileName.substring(0, fileName.lastIndexOf(".")) + ".properties");
        if (!propertiesFile.exists()) {
            try {
                propertiesFile.createNewFile();
            } catch (IOException e) {
                System.err.print("创建文件失败！");
                return;
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(propertiesFile);
            properties.store(fileWriter, "通过输入流向配置文件写入数据");
            fileWriter.close();
        } catch (IOException e) {
            System.err.print("向properties文件写入数据失败！");
            return;
        }
    }

    public static Properties readProperties(String propertiesFileName) {
        File file = new File(propertiesFileName);
        Properties properties = null;
        if (!file.exists()) {
            System.out.print("文件不存在！");
            return null;
        }

        try {
            FileReader fileReader = new FileReader(file);
            //创建一个Properties容器
            properties = new Properties();
            //从流中加载properties的文件信息
            properties.load(fileReader);
            //循环输出配置信息
            for (Object key: properties.keySet()) {
                System.out.println(key + "=" + properties.get(key));
            }

        } catch (Exception e) {
            System.out.print("文件不存在！");
        }
        return properties;
    }

}
