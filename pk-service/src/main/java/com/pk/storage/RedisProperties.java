package com.pk.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

/**
 * @author: Roy
 * @description: redis的配置文件
 * @create: 2020-04-14 17:56
 * @since: JDK1.8
 */
@Configuration

public class RedisProperties {

    //调用的请求路径
    @Value(value = "${spring.redis.host}")
    private String host;
    //调用的端口号
    @Value(value = "${spring.redis.port:0}")
    private int port;
    //调用的用户名
    @Value(value = "${spring.redis.appId}")
    private String appId;
    //调用的密码
    @Value(value = "${spring.redis.secret}")
    private String secret;
    //db库索引，默认为0（0-15）
    @Value(value = "${spring.redis.database:0}")
    private int database;
    //最大等待时间
    @Value(value = "${spring.redis.maxWaitMillis:3000}")
    private int maxWaitMillis;
    //最大空闲的jedis实例，默认20
    @Value(value = "${spring.redis.maxIdle:15}")
    private int maxIdle;
    //最大连接池数量，默认30
    @Value(value = "${spring.redis.maxTotal:30}")
    private int maxTotal;
    @Value(value = "${spring.redis.timeout:30000}")
    private long timeout;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public boolean isDefault(){
        return isBlank(host) && port == 0;
    }

    public boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    //读取配置文件中的redis配置
    public void readProperties(){
        Properties properties;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("properties");
            //遍历取值
            Enumeration<?> enumeration = properties.propertyNames();

            Set<Object> objects = properties.keySet();
            for (Object object : objects) {
                System.out.println(new String(properties.getProperty((String) object).getBytes("iso-8859-1"), "gbk"));
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
