package com.pk.generator;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: Roy
 * @description: 主键生成规则
 * @create: 2020-04-10 10:36
 * @since: JDK1.8
 */
public class Strategy implements InitializingBean, DisposableBean {

    public static final String LONG_VALUE = "longValue";

    public static Map<String, AtomicLong> initializationCode = new ConcurrentHashMap<>();

    @Value("${machine.code}")
    private Long machineCode;

    @Override
    public void afterPropertiesSet() {
        //机器码是0～16的话，需要先转为Long
        Long value = Long.valueOf(machineCode);
        //将机器码向左移59位，即符号位的后面
        long starValue = value << 59;
        //获取当前时间
        long timeMillis = System.currentTimeMillis();
        //将两个long类型的二进制数值进行异或得到的值作为容器初始化后的初始流水号
        long endValue = starValue ^ timeMillis;
        //把初始值赋给AtomicLong
        AtomicLong atomicLong = new AtomicLong(endValue);
        //将初始值放到Map里，便于后面读取
        initializationCode.put(LONG_VALUE, atomicLong);
    }

    @Override
    public void destroy() {
        initializationCode.clear();
    }

    public static Long getFlowCode(boolean initialization){
        if (initialization) {
            //如果是第一次获取，直接获取Map中的值，返给需要获取的对象
            AtomicLong atomicLong = initializationCode.get(LONG_VALUE);
            return atomicLong.get();
        }
        AtomicLong atomicLong = initializationCode.get(LONG_VALUE);
        long value = atomicLong.incrementAndGet();
        initializationCode.clear();
        initializationCode.put(LONG_VALUE, new AtomicLong(value));
        return value;
    }

    public static void main(String[] args) {
        Long code = Long.valueOf(1);
        long l = code << 59;

        System.out.println("code: " + Long.toBinaryString(code));
        System.out.println("code: " + Long.toBinaryString(code<<59));
        long timeMillis = System.currentTimeMillis();
        System.out.println("timeMillis: " + timeMillis);
        System.out.println("timeMillis: " + Long.toBinaryString(timeMillis));

        long l1 = l ^ timeMillis;
        System.out.println("endValue: " + Long.toBinaryString(l1));
    }

}
