package com.madking.peon.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;


@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(SwaggerConfig.basePackage("com.madking.peon.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                return declaringClass(input).transform(handlerPackage(basePackage)).or(true);
            }
        };
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return new Function<Class<?>, Boolean>() {
            @Override
            public Boolean apply(Class<?> input) {
                for (String strPackage : basePackage.split(",")) {
                    boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                    if (isMatch) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("peon")
                .description("APIs documentation for peon")
                .version("1.0")
                .build();
    }

}