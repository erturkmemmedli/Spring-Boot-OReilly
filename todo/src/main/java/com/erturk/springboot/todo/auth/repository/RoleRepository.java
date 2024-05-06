package com.erturk.springboot.todo.auth.repository;

import java.util.Optional;

import com.erturk.springboot.todo.auth.entities.ERole;
import com.erturk.springboot.todo.auth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}