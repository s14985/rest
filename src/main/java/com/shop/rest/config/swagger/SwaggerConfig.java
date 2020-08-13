package com.shop.rest.config.swagger;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  // http://localhost:8082/v3/api-docs/
  // http://localhost:8082/swagger-ui.html

  @Bean
  public Docket apiDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.any())
      .paths(PathSelectors.any())
      .build()
      .apiInfo(createApiInfo());
  }

  private ApiInfo createApiInfo() {
    return new ApiInfo(
      "E-commerce REST API",
      "REST API prototype for BSc Thesis",
      "1.0",
      "https://github.com/s14985/rest",
      new Contact(
        "Adam Sikora",
        "https://github.com/s14985/rest",
        "s14985@pjwstk.edu.pl"
      ),
      "GNU GPL",
      "http://www.gnu.org/licenses/gpl-3.0.html",
      Collections.emptyList()
    );
  }
}
