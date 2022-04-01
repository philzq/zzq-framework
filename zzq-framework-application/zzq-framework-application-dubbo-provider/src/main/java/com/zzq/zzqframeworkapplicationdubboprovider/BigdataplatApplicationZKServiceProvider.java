package com.zzq.zzqframeworkapplicationdubboprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 *

 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.zzq.**")
@EnableDubbo(scanBasePackages = {"com.zzq.**"})
public class BigdataplatApplicationZKServiceProvider {

    public static void main(String[] args) {
        SpringApplication.run(BigdataplatApplicationZKServiceProvider.class, args);
    }
}
