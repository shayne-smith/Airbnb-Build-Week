package com.lambdaschool.airbnbbuildweek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configures the default Swagger Documentation
 */
@Configuration
@EnableSwagger2
public class Swagger2Config
{
    /**
     * Configures what to document using Swagger
     *
     * @return A Docket which is the primary interface for Swagger configuration
     */
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors
                .basePackage("com.lambdaschool.airbnbbuildweek"))
            .paths(PathSelectors.regex("/.*"))
            .build()
            .useDefaultResponseMessages(false) // Allows only my exception responses
            .apiInfo(apiEndPointsInfo());
    }

    /**
     * Configures some information related to the Application for Swagger
     *
     * @return ApiInfo a Swagger object containing identification information for this application
     */
    private ApiInfo apiEndPointsInfo()
    {
        return new ApiInfoBuilder().title("AirBnB Optimal Price Calculator")
            .description("An optimal price calculator to help AirBnB hosts determine the best price for their new property listing")
            .contact(new Contact("Shayne Smith",
                "https://www.shaynesmith.io",
                "shayne.m.smith@vanderbilt.edu"))
            .license("MIT")
            .licenseUrl("https://github.com/shayne-smith/Airbnb-Build-Week/blob/master/LICENSE")
            .version("1.0.0")
            .build();
    }
}