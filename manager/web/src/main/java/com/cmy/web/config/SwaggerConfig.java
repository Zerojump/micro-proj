package com.cmy.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Lankidd on 2017/4/4.
 */
@Configuration
@EnableSwagger2//Swagger 2 is enabled through the @EnableSwagger2 annotation.
public class SwaggerConfig {
    @Bean
    public Docket api() {
        /*
        After the Docket bean is defined, its select() method returns an instance of ApiSelectorBuilder,
        which provides a way to control the endpoints exposed by Swagger.
        Predicates for selection of RequestHandlers can be configured with the help of RequestHandlerSelectors and PathSelectors.
        Using any() for both will make documentation for your entire API available through Swagger.
        This configuration is enough to integrate Swagger 2 into existing Spring Boot project.
        For other Spring projects, some additional tuning is required.

        You can restrict Swagger’s response by passing parameters to the apis() and paths() methods of the Docket class.
        As seen above, RequestHandlerSelectors allows using the any or none predicates,
        but can also be used to filter the API according to base package, class annotation, and method annotations.
        PathSelectors provides additional filtering with predicates which scan the request paths of your application.
        You can use any(), none(), regex(), or ant().
        In the example below we will instruct Swagger to include only controllers from a specific package,
        with specific paths, using the ant() predicate.
         */
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.cmy.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "My REST API",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                "myeaddress@company.com",
                "License of API",
                "API license URL");
        return apiInfo;
    }
}
