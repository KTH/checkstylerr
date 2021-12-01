package com.ctrip.framework.apollo.demo.spring.springBootDemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
@ConfigurationProperties(prefix = "redis.cache")
@Component
public class SampleRedisConfig {
  private static final Logger logger = LoggerFactory.getLogger(SampleRedisConfig.class);

  private int expireSeconds;
  private String clusterNodes;
  private int commandTimeout;

  @PostConstruct
  private void init() {
    logger.info("ConfigurationProperties sample - expireSeconds: {}, clusterNodes: {}, commandTimeout: {}",
        expireSeconds, clusterNodes, commandTimeout);
  }

  public void setExpireSeconds(int expireSeconds) {
    this.expireSeconds = expireSeconds;
  }

  public void setClusterNodes(String clusterNodes) {
    this.clusterNodes = clusterNodes;
  }

  public void setCommandTimeout(int commandTimeout) {
    this.commandTimeout = commandTimeout;
  }
}
