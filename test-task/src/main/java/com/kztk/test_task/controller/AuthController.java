package com.kztk.test_task.controller;

import com.kztk.test_task.model.User;
import com.kztk.test_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/auth/telegram")
    // ResponseEntity<?> - любое тело ответа
    public ResponseEntity<?> auth(@RequestBody Map<String, String> data) {
        // Заполнение пользователя
        User user = new User();
        user.setId(Long.parseLong(data.get("id")));
        user.setFirstName(data.get("first_name"));
        user.setLastName(data.get("last_name"));
        user.setUsername(data.get("username"));

        // Сохранение пользователя в бд
        userRepository.save(user);
        return ResponseEntity.ok().build(); // пустое тело ответа
    }


}
