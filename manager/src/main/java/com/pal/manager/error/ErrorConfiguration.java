package com.pal.manager.error;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * ErrorController注册
 *
 * @author pal
 */
@Configuration
public class ErrorConfiguration {
    @Bean
    public ErrorController basicErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties,
                                                ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        return new ErrorController(errorAttributes, serverProperties.getError(),
                errorViewResolversProvider.getIfAvailable());
    }
}
