package com.alibou.security.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @GetMapping("")
    public String get() {
        return "GET::admin_controller";
    }

    @PostMapping
    public String post() {
        return "POST::admin_controller";
    }

    @PutMapping
    public String put() {
        return "PUT admin_controller";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE admin_controller";
    }
}
