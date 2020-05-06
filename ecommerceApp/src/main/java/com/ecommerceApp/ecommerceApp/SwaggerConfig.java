package com.ecommerceApp.ecommerceApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.Arrays;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final Contact DEFAULT_CONTACT = new Contact("Apoorva", "", "");
    public static final ApiInfo DEFAULT = new ApiInfo("My API DOCUMENTATION", "My Api Documentation", "1.0", "urn:tos",
            DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.ecommerceApp.ecommerceApp.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(DEFAULT)
                .securitySchemes(Arrays.asList(apiKey()));

    }
    private ApiKey apiKey(){
        return new ApiKey("Bearer","Authorization","header");
    }
}