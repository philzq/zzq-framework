package com.zzq.zzqframeworkdatakafka.config.ka;

import com.zzq.zzqframeworkdatakafka.config.KafkaConfigConstant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.Map;

/**
 * KaConfiguration
 *
 * @author zhouzhiqiang
 * @version 1.0
 * @date 2022-03-17 18:34
 */
@Configuration
public class KaConfiguration {

    @Bean("kaKafkaProperties")
    @ConfigurationProperties("spring.kafka.ka")
    @Primary
    public KafkaProperties kaKafkaProperties() {
        return new KafkaProperties();
    }

    @Bean(KafkaConfigConstant.KA_KAFKA_TEMPLATE)
    @Primary
    public KafkaTemplate<String, String> kaKafkaTemplate(@Qualifier("kaKafkaProperties") KafkaProperties kafkaProperties) {
        Map<String, Object> producerConfig = kafkaProperties.buildProducerProperties();
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfig));
    }

    @Bean(KafkaConfigConstant.KA_KAFKA_CONTAINER_FACTORY)
    @Primary
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kaKafkaContainerFactory(@Qualifier("kaKafkaProperties") KafkaProperties kafkaProperties) {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        Map<String, Object> consumerConfig = kafkaProperties.buildConsumerProperties();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfig));
        factory.setConcurrency(kafkaProperties.getListener().getConcurrency());
        factory.setMissingTopicsFatal(kafkaProperties.getListener().isMissingTopicsFatal());
        //设置手动提交
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }


}
