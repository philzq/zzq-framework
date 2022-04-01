package com.zzq.zzqframeworkapplicationdubboprovider.config;

import com.zzq.zzqframeworkservicecommon.utils.SwaggerConfigUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger2的接口配置
 *

 */
@Configuration
public class SwaggerConfig{


    @Bean
    public Docket api() {
        return SwaggerConfigUtil.api("com.zzq.bigdataplatapplicationzkserviceprovider.controller", "bigdataplat-application-restapi", "0.0.1-SNAPSHOT");
    }
}
