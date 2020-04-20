package com.pk.generator;

import com.pk.storage.RedisProperties;
import com.pk.storage.RedisStorage;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.time.Duration;

import static com.pk.storage.RedisStorage.REDIS_KEY;

/**
 * @author: Roy
 * @description: 主键生成规则
 * @create: 2020-04-10 10:36
 * @since: JDK1.8
 */
@Configuration
public class Strategy {

    private static Logger log = LoggerFactory.getLogger(Strategy.class);

    @Autowired
    private RedisProperties redisProperties;

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

    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        if (redisProperties.isDefault()){
            log.info("================没有redis的配置，不创建redisTemplate==============");
            return null;
        }
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
        configuration.setDatabase(redisProperties.getDatabase());
        if (redisProperties.getSecret() != null && redisProperties.getSecret() != ""){
            configuration.setPassword(redisProperties.getSecret());
        }

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(redisProperties.getMaxIdle());
        config.setMaxTotal(redisProperties.getMaxTotal());
        config.setMaxWaitMillis(redisProperties.getMaxWaitMillis());
        JedisClientConfiguration build = JedisClientConfiguration.builder().usePooling().poolConfig(config).and().readTimeout(Duration.ofMillis(redisProperties.getTimeout())).build();

        //根据redis的配置创建工厂
        RedisConnectionFactory factory = new JedisConnectionFactory(configuration, build);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        //设置序列化方式
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        redisTemplate.setDefaultSerializer(serializer);
        return redisTemplate;
    }

    @Bean
    public RedisStorage redisStorage (@Qualifier("afterPropertiesSet") InitBean initBean,
                                      @Qualifier("redisTemplate") RedisTemplate redisTemplate){
        if (redisProperties.isDefault()){
            log.info("================没有redis的配置，不加载redis的bean==============");
            return null;
        }
        //从initBean中获取当前主键
        long storageCode = initBean.getStorageCode();
        //将当前主键存到redis
        redisTemplate.opsForValue().set(REDIS_KEY, storageCode);
        return new RedisStorage(initBean, redisProperties, redisTemplate);
    }

}
