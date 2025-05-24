package com.kztk.test_task.exception;

import com.kztk.test_task.util.ExceptionMessageProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {


    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId, ExceptionMessageProvider messageProvider) {
        super(messageProvider.getUserNotFoundMessage(userId));
    }

}
