package org.chench.springboot.scaffolding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 集成swagger进行API文档管理
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.config.SwaggerConfig
 * @date 2023.07.17
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        // 自定义响应消息
        List<ResponseMessage> responseMessageList = new ArrayList<ResponseMessage>();
        responseMessageList.add(new ResponseMessageBuilder()
                .code(500)
                .message("服务端发生异常")
                .responseModel(new ModelRef("Error"))
                .build());
        responseMessageList.add(new ResponseMessageBuilder()
                .code(403)
                .message("资源不可用")
                .build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageList);
    }

    /**
     * 声明API版本信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "SpringBoot Scaffolding project API文档",
                "SpringBoot脚手架工程项目API文档说明",
                "API V1.0",
                "Terms of service",
                new Contact("chench", "https://www.cnblogs.com/nuccch/", "nuccch2010@163.com"),
                "Apache",
                "http://www.apache.org/", Collections.emptyList()
        );
    }
}
