package com.zzq.zzqframeworkservicecommon.utils;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 〈功能简述〉<br>
 * 〈swaggerConfig〉
 *
 * @author zhouzhiqiang
 * @create 2018-12-04
 */
public class SwaggerConfigUtil {
    public static Docket api(String packagePath, String title, String version) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(title, version))
                .select()
                //自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage(packagePath))
                .paths(PathSelectors.any())
                .build();
    }

    private static ApiInfo apiInfo(String title, String version) {
        return new ApiInfoBuilder()
                //页面标题
                .title(title)
                //版本号
                .version(version)
                .build();
    }
}

