package com.hmdp.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: hm-dianping
 * @BelongsPackage: com.hmdp.config
 * @Author: zt
 * @CreateTime: 2023-03-25  22:56
 * @Description:
 */

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(){
        //配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.126.100:6379").setPassword("ytyt420ztzt");
        //创建RedissonClient对象
        return Redisson.create(config);
    }

}
