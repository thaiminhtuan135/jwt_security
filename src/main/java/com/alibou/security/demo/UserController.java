package com.alibou.security.demo;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @GetMapping("")
    public String get() {
        return "GET::user_controller";
    }

    @PostMapping
    public String post() {
        return "POST::user_controller";
    }

    @PutMapping
    public String put() {
        return "PUT user_controller";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE user_controller";
    }
}
