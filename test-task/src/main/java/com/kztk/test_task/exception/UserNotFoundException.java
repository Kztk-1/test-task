package com.kztk.test_task.exception;

import com.kztk.test_task.util.ExceptionMessageProvider;

public class UserNotFoundException extends RuntimeException {


    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId, ExceptionMessageProvider messageProvider) {
        super(messageProvider.getUserNotFoundMessage(userId));
    }

}
