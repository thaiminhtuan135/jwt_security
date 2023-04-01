package com.alibou.security.auth;

import com.alibou.security.Entity.Student;
import com.alibou.security.service.UserService;
import com.alibou.security.user.Role;
import com.alibou.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    var student = Student.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .role(Role.USER)
            .build();
    Set<ConstraintViolation<Student>> violations = validator.validate(student);
    if (!violations.isEmpty()) {
      List<String> errors = violations.stream()
              .map(ConstraintViolation::getMessage)
              .collect(Collectors.toList());
      return ResponseEntity.badRequest().body(errors);
    }
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }
}
