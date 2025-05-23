package com.kztk.test_task.controller;

import com.kztk.test_task.exception.UserNotFoundException;
import com.kztk.test_task.model.User;
import com.kztk.test_task.repository.UserRepository;
import com.kztk.test_task.util.ExceptionMessageProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserRepository userRepository;
    private final ExceptionMessageProvider messageProvider;

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(@RequestParam Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId, messageProvider));
        return ResponseEntity.ok(user);
    }
}