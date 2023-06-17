package com.alibou.security.auth;

import com.alibou.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> register(
          @RequestBody AuthenticationRequest request
  ) {
    if (userService.existEmail(request.getEmail(),null)) {
      return new ResponseEntity<>("Email already exists, please enter another email", HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/check-exist-email/{email}")
  public ResponseEntity<?> checkEmail(@PathVariable String email) {
    if (userService.existEmail(email,null)) {
      return new ResponseEntity<>("Email already exists, please enter another email", HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
