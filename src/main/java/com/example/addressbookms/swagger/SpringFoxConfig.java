/*
 * Copyright 2020 by Urbanise.  All Rights Reserved.
 *
 * This software is the proprietary information of Majitek.
 * Use is subject to license terms.
 */

package com.example.addressbookms.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/address-service/api/.*"))
                .paths(PathSelectors.regex("/error.*").negate())
                .paths(PathSelectors.regex("/actuator.*").negate())
                .build();
    }
}
