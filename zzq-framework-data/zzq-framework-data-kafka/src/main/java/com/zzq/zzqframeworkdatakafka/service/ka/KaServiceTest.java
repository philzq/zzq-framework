package com.zzq.zzqframeworkdatakafka.service.ka;

import com.zzq.zzqframeworkdatakafka.config.KafkaConfigConstant;
import com.zzq.zzqframeworkdatakafka.config.KafkaTopicDynamicInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * katest
 *
 * @author zhouzhiqiang
 * @version 1.0
 * @date 2022-03-17 18:52
 */
@Service
public class KaServiceTest {

    @Autowired
    @Qualifier(KafkaConfigConstant.KA_KAFKA_TEMPLATE)
    private KafkaTemplate<String, String> kaKafkaTemplate;

    public void sendMessage(String message) {
        kaKafkaTemplate.send(KafkaTopicDynamicInit.KaTopic.getAlertRecord(), message).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {

            }
        });
        kaKafkaTemplate.send(KafkaTopicDynamicInit.KaTopic.getRouteTrace(), message);
        kaKafkaTemplate.send(KafkaTopicDynamicInit.KaTopic.getExceptionAnalysis(), message);
    }


}
