package com.zhixi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author zhangzhixi
 * @version 1.0
 * @date 2021-12-14 11:17
 */
@Configuration
public class SwaggerConfig {


    // 配置Swagger
    @Bean
    public Docket docket(Environment environment) {
        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev", "test");
        //获取项目的环境：判断是否处在自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);
        // 返回docket的bean实例
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag);//是否启用Swagger，false则不启用
    }

    // 配置swagger的信息-api-info
    private ApiInfo apiInfo() {
        // 作者说明
        Contact contact = new Contact("志喜", "https://www.cnblogs.com/zhangzhixi", "1820712542@qq.com");
        return new ApiInfo(
                "志喜的SwaggerAPI文档",
                "人生没有白走的路~",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
