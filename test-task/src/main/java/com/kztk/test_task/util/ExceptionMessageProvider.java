package com.kztk.test_task.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExceptionMessageProvider {

    @Value("${error.messages.user_not_found}")
    private String notFoundTemplate;

    public String getUserNotFoundMessage(Long userId) {
        return String.format(notFoundTemplate, userId);
    }
}