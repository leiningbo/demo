package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author leiningbo
 * @date 10点02分  2019-08-19
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Value("${system.swagger.host}")
    private String host;

    /**
     * 生成环境下记得设置为false
     */
    @Value("${system.swagger.enable}")
    private Boolean enable;

    @Bean
    public Docket ceateRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .host(host)
                .apiInfo(apiInfo())
                .select()
                //暴露接口地址的包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restful风格，https://www.cnblogs.com/abo666/")
                .termsOfServiceUrl("http://127.0.0.1:8080/")
                .version("1.0")
                .build();
    }

}
