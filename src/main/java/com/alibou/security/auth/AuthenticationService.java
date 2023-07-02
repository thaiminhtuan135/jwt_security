package com.alibou.security.auth;

import com.alibou.security.Entity.KieuThanhVien;
import com.alibou.security.config.JwtService;
import com.alibou.security.Entity.Token;
import com.alibou.security.repo.KieuThanhVienRepository;
import com.alibou.security.repo.TokenRepository;
import com.alibou.security.Enum.TokenType;
import com.alibou.security.Enum.Role;
import com.alibou.security.Entity.User;
import com.alibou.security.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final KieuThanhVienRepository kieuThanhVienRepository;

  public ResponseEntity<?> register(AuthenticationRequest request) {
    KieuThanhVien kieuThanhVien = kieuThanhVienRepository.findById(1).get();
    var user = User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER).kieuThanhVien(kieuThanhVien)
            .kieu_thanh_vien_id(kieuThanhVien.getId())
            .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
//    return AuthenticationResponse.builder()
//            .token(jwtToken)
//            .build();
    return new ResponseEntity<>("Register successfully", HttpStatus.CREATED);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    var user = repository.existsByEmail(request.getEmail(),null)
            .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
