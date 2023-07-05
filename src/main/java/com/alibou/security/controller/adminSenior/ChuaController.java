package com.alibou.security.controller.adminSenior;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin-senior/chua")
@PreAuthorize("hasRole('ADMIN_SENIOR')")
public class ChuaController {
    @GetMapping
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<>("dasd", HttpStatus.OK);
    }
}
