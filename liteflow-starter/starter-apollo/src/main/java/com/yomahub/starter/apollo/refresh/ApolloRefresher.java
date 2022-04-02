package com.yomahub.starter.apollo.refresh;

import cn.hutool.core.util.StrUtil;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.yomahub.liteflow.enums.FlowParserTypeEnum;
import com.yomahub.liteflow.flow.FlowBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * ApolloRefresher related
 *
 * @author: liqingyan
 * @since 1.0.0
 **/
public class ApolloRefresher implements ConfigChangeListener, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(ApolloRefresher.class);

    @Value("${apollo.ruleSource.namespaces:application}")
    private String namespace;

    @Value("${apollo.ruleSource.key:}")
    private String apolloKey;

    @Value("${apollo.ruleSource.format:}")
    private String configFormat;

    private ConfigFileFormat configFileFormat;

    @Override
    public void afterPropertiesSet() {

        String[] apolloNamespaces = this.namespace.split(",");
        String realNamespace = apolloNamespaces[0];

        configFileFormat = deduceFileType(configFormat);
        namespace = realNamespace.replaceAll("." + configFileFormat.getValue(), "");

        Config config = ConfigService.getConfig(realNamespace);

        try {
            config.addChangeListener(this);
            log.info("apollo refresher, add listener success, namespace: {}", realNamespace);
        } catch (Exception e) {
            log.error("apollo refresher, add listener error, namespace: {}", realNamespace, e);
        }
    }

    @Override
    public void onChange(ConfigChangeEvent changeEvent) {
        Config config = ConfigService.getConfig(namespace);
        if (StrUtil.isNotBlank(apolloKey)) {
            String content = config.getProperty(apolloKey, "");
            try {
                FlowParserTypeEnum typeEnum = FlowParserTypeEnum.of(configFileFormat.getValue());
                if (typeEnum != null) {
                    FlowBus.refreshFlowMetaData(typeEnum, content);
                }
            } catch (Exception e) {
                log.error("apollo refresher, refresh config error", e);
            }
        }
    }

    private ConfigFileFormat deduceFileType(String configFormat) {
        ConfigFileFormat configFileFormat = ConfigFileFormat.Properties;
        if (configFormat.contains(ConfigFileFormat.YAML.getValue())) {
            configFileFormat = ConfigFileFormat.YAML;
        } else if (configFormat.contains(ConfigFileFormat.YML.getValue())) {
            configFileFormat = ConfigFileFormat.YML;
        } else if (configFormat.contains(ConfigFileFormat.JSON.getValue())) {
            configFileFormat = ConfigFileFormat.JSON;
        } else if (configFormat.contains(ConfigFileFormat.XML.getValue())) {
            configFileFormat = ConfigFileFormat.XML;
        } else if (configFormat.contains(ConfigFileFormat.TXT.getValue())) {
            configFileFormat = ConfigFileFormat.TXT;
        }

        return configFileFormat;
    }
}
