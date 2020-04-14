package com.pk.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Roy
 * @description: 主键生成规则
 * @create: 2020-04-10 10:36
 * @since: JDK1.8
 */
@Configuration
public class Strategy {

    @Autowired
    private InitBean initBean;

    @Bean
    public InitBean afterPropertiesSet() {
        if (initBean == null){
            initBean = new InitBean();
        }
        GainNextPk.setInitBean(initBean);
        return initBean;
    }

}
