package com.zzq.zzqframeworkapplicationconsumer.controller;

import com.zzq.zzqframeworkdatakafka.service.ka.KaServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * KafkaProducer
 *
 * @author zhouzhiqiang
 * @version 1.0
 * @date 2022-03-17 18:03
 */

@RestController
public class KafkaProducer {
    @Autowired
    private KaServiceTest kaServiceTest;

    // 发送消息
    @GetMapping("/kafka/normal/{message}")
    public void sendMessage1(@PathVariable("message") String message) {
        kaServiceTest.sendMessage(message);
    }
}

