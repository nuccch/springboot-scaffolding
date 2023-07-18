package org.chench.springboot.scaffolding.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Redis配置参数
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.config.RedisProperties
 * @date 2023.07.17
 */
@Component
public class RedisProperties {
    @Value("${spring.redis.cluster.nodes:127.0.0.1:6379}")
    private String springRedisClusterNodes;

    @Value("${spring.redis.timeout:5000}")
    private Integer springRedisTimeout;

    public String getSpringRedisClusterNodes() {
        return springRedisClusterNodes;
    }

    public void setSpringRedisClusterNodes(String springRedisClusterNodes) {
        this.springRedisClusterNodes = springRedisClusterNodes;
    }

    public Integer getSpringRedisTimeout() {
        return springRedisTimeout;
    }

    public void setSpringRedisTimeout(Integer springRedisTimeout) {
        this.springRedisTimeout = springRedisTimeout;
    }
}
