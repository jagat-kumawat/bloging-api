package com.blog.config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityRequirement;



import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class swaggerconfig {

    @Bean
//    public Docket swaggerdocket() {
//
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
//
//    }
    public GroupedOpenApi publicApi() {

        return GroupedOpenApi.builder().pathsToMatch("/**").build();
    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        return  new OpenAPI()
                .info(new Info().title("Spring blog API")
                        .description("Spring simple blog api application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop blog api Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

//    @Bean
//    public OpenApiCustomiser openApiCustomiser() {
//        return openApi -> {
//            // Define a security scheme
//            SecurityScheme securityScheme = new SecurityScheme()
//                    .type(Type.HTTP)
//                    .scheme("bearer")
//                    .bearerFormat("JWT")
//                    .name("JWT Authorization")
//                    .in(In.HEADER);
//
//            // Add the security scheme to the OpenAPI object
//            openApi.addSecurityItem(new SecurityRequirement().addList(securityScheme.getName()));
//            openApi.getComponents().addSecuritySchemes("BearerAuth", securityScheme);
//
//            //    private ApiInfo getInfo() {
////
////       return new ApiInfo("jagat", "blogapi", "1.0", "http://jagatteharpuria.com", "jagat", "license of blog api", "Api licnse url");
//        };

    }