package com.pk.generator;

import static com.pk.generator.Strategy.initializationCode;

/**
 * @author: Roy
 * @description: 获取下一主键
 * @create: 2020-04-13 09:29
 * @since: JDK1.8
 */
public class GainNextPk {

    public static Long getFlowCode(){
        return initializationCode.incrementAndGet();
    }

}
