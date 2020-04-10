package com.pk.generator;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: Roy
 * @description: 主键生成规则
 * @create: 2020-04-10 10:36
 * @since: JDK1.8
 */
public class Strategy implements InitializingBean, DisposableBean {

    private static AtomicLong initializationCode;

    @Value("${machine.code:1}")
    private Long machineCode;

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

    @Override
    public void destroy() {

    }

    public static Long getFlowCode(){
        return initializationCode.incrementAndGet();
    }

}
