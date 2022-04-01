package com.zzq.zzqframeworkapplicationconsumer;

import com.zzq.zzqframeworkdatakafka.config.KafkaTopicDynamicInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author zhouzhiqiang
 * @version 1.0
 * @date 2022-03-16 17:59
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.zzq.**")
public class BigdataplatApplicationConsumer {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BigdataplatApplicationConsumer.class);
        springApplication.addInitializers(new KafkaTopicDynamicInit());
        springApplication.run(args);
    }
}
