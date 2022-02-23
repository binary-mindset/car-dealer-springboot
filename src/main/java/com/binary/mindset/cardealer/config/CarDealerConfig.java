package com.binary.mindset.cardealer.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@Configuration
@EnableSwagger2
@EnableJpaRepositories(basePackages = "com.binary.mindset.cardealer.repository")
@EntityScan(basePackages = "com.binary.mindset.cardealer.repository.entity")
@ComponentScan(basePackages = {"com.binary.mindset.cardealer"})
public class CarDealerConfig {

    @Bean
    public Docket api() {
        final String BASE_PACKAGE = "com.binary.mindset.cardealer";
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Car Dealer API")
                        .description("Car Dealer API description")
                        .contact(new Contact("Chapter Backend", "#chapter_backend", "ivan.perez@parserdigital.com"))
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .apis(Predicate.not(RequestHandlerSelectors.basePackage(BASE_PACKAGE + ".swagger")))
                .build();
    }
}