package com.pk.generator;

import org.springframework.beans.factory.annotation.Qualifier;
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

    @Bean
    public GainNextPk gainNextPk(@Qualifier("afterPropertiesSet") InitBean afterPropertiesSet){
        return new GainNextPk(afterPropertiesSet);
    }

    @Bean
    public InitBean afterPropertiesSet() {
        InitBean initBean = new InitBean();
        if (initBean == null){
            throw new RuntimeException("ex = initBean 为null，对不起，请继续努力");
        }
        StaticUtils.setInitBean(initBean);
        return initBean;
    }

    @Bean
    public StaticUtils stacticUtils(){
        return new StaticUtils();
    }

}
