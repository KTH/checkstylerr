package me.jcala.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by zhipeng.zuo on 2017/8/30.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder (ConfigServerApplication.class).web(true).run(args);
  }

}
