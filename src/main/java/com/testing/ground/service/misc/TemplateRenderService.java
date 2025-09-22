package com.testing.ground.service.misc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemplateRenderService {
    private final SpringTemplateEngine templateEngine;

    public String render(String templateContent, Map<String, Object> variables) {
        var context = new Context();
        context.setVariables(variables);
        return templateEngine.process(templateContent, context);
    }
}
