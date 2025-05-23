package com.kztk.test_task.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
}
