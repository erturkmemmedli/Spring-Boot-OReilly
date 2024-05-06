package com.erturk.springboot.todo.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoControllerRest {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity<List<Todo>> listAllTodos() {
        String username = getLoggedInUsername();
        List<Todo> todos = todoRepository.findByUsername(username);
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@Valid @RequestBody Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        String username = getLoggedInUsername();
        todo.setUsername(username);
        todoRepository.save(todo);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/api/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int id, @Valid @RequestBody Todo todo, BindingResult result)
            throws ChangeSetPersister.NotFoundException {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        existingTodo.setDescription(todo.getDescription() != null ? todo.getDescription() : existingTodo.getDescription());
        existingTodo.setTargetDate(todo.getTargetDate() != null ? todo.getTargetDate() : existingTodo.getTargetDate());
        todoRepository.save(existingTodo);
        return ResponseEntity.ok(existingTodo);
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
