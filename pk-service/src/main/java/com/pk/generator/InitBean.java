package com.pk.generator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: Roy
 * @description:
 * @create: 2020-04-13 15:00
 * @since: JDK1.8
 */
@ConfigurationProperties(prefix="machine.code")
public class InitBean implements InitializingBean {

    @Value("${number:0}")
    private long machineCode;

    public long getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(long machineCode) {
        this.machineCode = machineCode;
    }

    private AtomicLong initializationCode;

    public long getInitializationCode(){
        return initializationCode.incrementAndGet();
    }

    @Override
    public void afterPropertiesSet() {
        //将机器码向左移59位，即符号位的后面
        long starValue = machineCode << 59;
        //获取当前时间
        long timeMillis = System.currentTimeMillis();
        //将两个long类型的二进制数值进行异或得到的值作为容器初始化后的初始流水号
        long endValue = starValue ^ timeMillis;
        //把初始值赋给AtomicLong
        initializationCode = new AtomicLong(endValue);
    }

}
