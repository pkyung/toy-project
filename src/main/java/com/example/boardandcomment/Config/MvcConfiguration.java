package com.example.boardandcomment.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    public void addResourceHandlers(final ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
    }
}
