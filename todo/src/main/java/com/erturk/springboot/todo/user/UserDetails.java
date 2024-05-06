package com.erturk.springboot.todo.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String role;

    public UserDetails(String name, String role) {
        super();
        this.name = name;
        this.role = role;
    }
}
