package org.chench.springboot.scaffolding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 应用全局配置
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.config.AppConfig
 * @date 2023.07.17
 */
@Configuration
public class AppConfig {
    // 注入Redis配置信息
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        JedisPoolConfig poolConfig=new JedisPoolConfig();
//        poolConfig.setMaxIdle(10);
//        poolConfig.setMinIdle(0);
//        poolConfig.setTestOnBorrow(true);
//        poolConfig.setTestOnReturn(true);
//        poolConfig.setTestWhileIdle(true);
//        poolConfig.setNumTestsPerEvictionRun(10);
//        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
//        JedisConnectionFactory factory = new JedisConnectionFactory(poolConfig);
//        factory.getStandaloneConfiguration().setHostName("localhost");
//        factory.getStandaloneConfiguration().setPort(6379);
//        return factory;
//    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws Exception {
        RedisTemplate<String, String> redisTemplateObject = new RedisTemplate<String, String>();
        redisTemplateObject.setConnectionFactory(redisConnectionFactory);
        setSerializer(redisTemplateObject);
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }

    // 使用String的数据结构的时候使用这个来更改序列化方式
    private void setSerializer(RedisTemplate<String, String> template) {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);
    }

    // Jedis Cluster访问集群
//    @Bean
//    public JedisCluster jedisCluster(RedisProperties properties) {
//        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//        //Jedis Cluster will attempt to discover cluster nodes automatically
//        String hosts = properties.getSpringRedisClusterNodes();
//        String[] arr = hosts.split(",");
//        int size = arr.length;
//        for (int i = 0; i < size; i++) {
//            String hostPort = arr[i];
//            String[] hostPortArr = hostPort.split(":");
//            jedisClusterNodes.add(new HostAndPort(hostPortArr[0], Integer.parseInt(hostPortArr[1])));
//        }
//        int timeout = properties.getSpringRedisTimeout();
//        JedisCluster jc = new JedisCluster(jedisClusterNodes, timeout, 3);
//        return jc;
//    }

    // Redisson客户端
//    @Bean
//    public RedissonClient RedissonClient(RedisProperties properties) {
//        // 1. Create config object
//        Config config = new Config();
//        String nodes = properties.getSpringRedisClusterNodes();
//        String[] nodeArr = nodes.split(",");
//        int size = nodeArr.length;
//        for (int i = 0; i < size; i++) {
//            String addr = new StringBuilder().append("redis://").append(nodeArr[i]).toString();
//            config.useClusterServers()
//                    // use "rediss://" for SSL connection
//                    .addNodeAddress(addr);
//        }
//        // Sync and Async API
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }
}
