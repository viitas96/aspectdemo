package com.clima.aspectdemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("1.0.0") String appVersion) {
        return new OpenAPI()
                .addServersItem(new Server()
                        .url(""))
                .info(new Info()
                        .title("AOP DEMO")
                        .version(appVersion)
                        .license(new License()
                                .name("AOP DEMO 1.0")
                                .url("https://dev.bitsoft.md/")));
    }

}
