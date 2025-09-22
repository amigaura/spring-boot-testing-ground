package com.testing.ground.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
public class AppConfig {

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        StringTemplateResolver stringResolver = new StringTemplateResolver();
        stringResolver.setTemplateMode(TemplateMode.HTML);
        stringResolver.setCheckExistence(false);
        engine.addTemplateResolver(stringResolver);
        return engine;
    }
}
