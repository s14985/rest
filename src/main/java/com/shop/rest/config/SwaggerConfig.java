package com.shop.rest.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

  // http://localhost:8082/v2/api-docs

  @Bean
  public Docket apiDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.any())
      .paths(PathSelectors.ant("/api/**"))
      .build()
      .apiInfo(createApiInfo());
  }

  // TODO
  private ApiInfo createApiInfo() {
    return new ApiInfo(
      "title",
      "description",
      "version",
      "termsOfServiceUrl",
      new Contact("name", "url", "email"),
      "licence",
      "licenseUrl",
      Collections.emptyList()
    );
  }
}
