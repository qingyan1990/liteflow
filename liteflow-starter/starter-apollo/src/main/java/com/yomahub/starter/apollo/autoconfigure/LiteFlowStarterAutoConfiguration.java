package com.yomahub.starter.apollo.autoconfigure;

import com.yomahub.starter.apollo.refresh.ApolloRefresher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ctrip.framework.apollo.spring.config.PropertySourcesConstants.APOLLO_BOOTSTRAP_ENABLED;

/**
 * LiteFlowStarterAutoConfiguration for apollo config center.
 *
 * @author: liqingyan
 * @since 1.0.0
 **/
@Configuration
@ConditionalOnClass(com.ctrip.framework.apollo.ConfigService.class)
@ConditionalOnProperty(value = {APOLLO_BOOTSTRAP_ENABLED},
        havingValue = "true", matchIfMissing = true)
public class LiteFlowStarterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ApolloRefresher apolloRefresher() {
        return new ApolloRefresher();
    }
}