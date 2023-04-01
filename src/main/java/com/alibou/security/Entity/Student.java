package com.alibou.security.Entity;

import com.alibou.security.token.Token;
import com.alibou.security.user.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank(message = "Firstname cannot be empty")
    private String firstname;
    @NotBlank(message = "Lastname cannot be empty")
    private String lastname;
    @NotBlank(message = "Email cannot be empty")
    private String email;
//    @NotBlank(message = "Password cannot be empty")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
