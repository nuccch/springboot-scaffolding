package org.chench.springboot.scaffolding.config;

import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Lettuce配置定制化
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.config.LettuceCustomizer
 * @date 2023.07.17
 */
@Component
public class LettuceCustomizer implements LettuceClientConfigurationBuilderCustomizer {
    @Override
    public void customize(LettuceClientConfiguration.LettuceClientConfigurationBuilder clientConfigurationBuilder) {
        // ClusterTopologyRefreshOptions配置用于开启自适应刷新和定时刷新。
        // 如自适应刷新不开启，Redis集群变更时将会导致连接异常！

        ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                //开启自适应刷新
                .enableAdaptiveRefreshTrigger(ClusterTopologyRefreshOptions.RefreshTrigger.MOVED_REDIRECT, ClusterTopologyRefreshOptions.RefreshTrigger.PERSISTENT_RECONNECTS, ClusterTopologyRefreshOptions.RefreshTrigger.UNKNOWN_NODE)
                .enableAllAdaptiveRefreshTriggers()
                .adaptiveRefreshTriggersTimeout(Duration.ofSeconds(3))
                //开启定时刷新,时间间隔根据实际情况修改
                .enablePeriodicRefresh(Duration.ofSeconds(3))
                .build();
//        redisClient.setOptions(ClusterClientOptions.builder()
//                .topologyRefreshOptions(topologyRefreshOptions)
//                .build());
        clientConfigurationBuilder.clientOptions(
                ClusterClientOptions.builder().topologyRefreshOptions(topologyRefreshOptions).build());
    }
}
