package com.erturk.springboot.todo.login;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@SessionAttributes("name")
@RequestMapping("api/welcome")
public class WelcomeController {
    @GetMapping
    public ResponseEntity<HashMap<String, String>> welcome() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put("name", getLoggedInUsername());
        return ResponseEntity.ok(user);
    }

    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
