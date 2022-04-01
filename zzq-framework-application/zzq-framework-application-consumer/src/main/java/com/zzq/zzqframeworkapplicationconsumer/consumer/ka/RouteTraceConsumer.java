package com.zzq.zzqframeworkapplicationconsumer.consumer.ka;

import com.zzq.zzqframeworkdatakafka.config.KafkaConfigConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * 轨迹数据 topic订阅
 *
 * @author zhouzhiqiang
 * @version 1.0
 * @date 2022-03-21 9:46
 */
@Component
public class RouteTraceConsumer {

    // 消费监听
    @KafkaListener(topics = {"#{'${kaKafkaTopicRouteTrace}'}"}, groupId = KafkaConfigConstant.KA_GROUP_ID, containerFactory = KafkaConfigConstant.KA_KAFKA_CONTAINER_FACTORY)
    public void onMessage(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
        //手动提交
        acknowledgment.acknowledge();
    }
}
