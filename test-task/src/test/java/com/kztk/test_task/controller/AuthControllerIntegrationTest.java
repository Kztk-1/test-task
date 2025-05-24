package com.kztk.test_task.controller;

import com.kztk.test_task.model.User;
import com.kztk.test_task.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void checkDbIsEmpty() {
        // проверка, что БД пуста перед тестом
        userRepository.deleteAll();
        assertEquals(0, userRepository.count(), "База данных не очищена перед тестом!");
    }

    @Test
    void testUserSavedToDatabase() throws Exception {
        // Отправляем POST-запрос с данными пользователя
        mockMvc.perform(post("/auth/telegram")
                        .contentType("application/json")
                        .content("""
                    {
                        "id": 123,
                        "first_name": "Ivan",
                        "last_name": "Petrov",
                        "username": "ivan_petrov"
                    }
                """))
                .andExpect(status().isOk());

        // Проверяем, что пользователь сохранен в БД
        User savedUser = userRepository.findById(123L).orElseThrow();
        assertEquals("Ivan", savedUser.getFirstName());
        assertEquals("Petrov", savedUser.getLastName());
    }

    @Test
    void testGetProfileExists() throws Exception {
        // Сначала создаем пользователя
        Long id = 123L;
        String firstName = "Ivan", lastName = "Petrov", username = "ivan_petrov";
        User user = new User(id, firstName, lastName, username);
        userRepository.save(user);

        // Проверяем получение профиля
        mockMvc.perform(get("/profile")
                        .param("userId", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName))
                .andExpect(jsonPath("$.username").value(username));
    }

    @Test
    void testGetProfileNotFound() throws Exception {
        mockMvc.perform(get("/profile")
                        .param("userId", "999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value("User with id 999 not found"));
    }

}