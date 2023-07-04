package com.alibou.security.controller.manager;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/manager")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {
    @GetMapping("")
    public String get() {
        return "GET::manager_controller";
    }

    @PostMapping
    public String post() {
        return "POST::manager_controller";
    }

    @PutMapping
    public String put() {
        return "PUT manager_controller";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE manager_controller";
    }
}
