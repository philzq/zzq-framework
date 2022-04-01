package com.zzq.zzqframeworkdatakafka.config;

import com.zzq.zzqframeworkcommon.entity.constant.SystemConstant;
import lombok.Getter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * KafkaTopic动态初始化
 * @author zhouzhiqiang
 * @version 1.0
 * @date 2022-03-18 16:54
 */
public class KafkaTopicDynamicInit implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        //部署环境
        String activeProfile = configurableApplicationContext.getEnvironment().getActiveProfiles()[0];
        //topic前缀
        String topicPrefix = SystemConstant.PROJECT_NAME + "-" + activeProfile + ".sync-";

        //初始化topic
        initTopic(topicPrefix);
    }

    /**
     * 初始化topic
     * @param topicPrefix
     */
    private void initTopic(String topicPrefix){
        //初始化ka kafka topic定义
        KaTopic.initKaTopic(topicPrefix);
    }

    public static class KaTopic{
        //topic属性定义方便外部使用
        //使用方式 kaKafkaTemplate.send(KafkaTopicDynamicInit.KaTopic.getAlertRecord(), message);
        @Getter
        private static String routeTrace;
        @Getter
        private static String alertRecord;
        @Getter
        private static String exceptionAnalysis;

        /**
         * 初始化ka kafka topic定义
         * @param topicPrefix
         */
        static void initKaTopic(String topicPrefix){
            //动态topic 定义
            //使用方式 @KafkaListener(topics = {"#{'${kaKafkaTopicAlertRecord}'}"}, groupId = KafkaConfigConstant.KA_GROUP_ID, containerFactory = KafkaConfigConstant.KA_KAFKA_CONTAINER_FACTORY)
            routeTrace = topicPrefix + "route-trace";
            alertRecord = topicPrefix + "alert-record";
            exceptionAnalysis = topicPrefix + "exception-analysis";
            System.setProperty("kaKafkaTopicRouteTrace", routeTrace);
            System.setProperty("kaKafkaTopicAlertRecord", alertRecord);
            System.setProperty("kaKafkaTopicExceptionAnalysis", exceptionAnalysis);
        }
    }
}
