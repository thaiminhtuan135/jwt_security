package com.alibou.security.config;

import com.alibou.security.user.Role;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.alibou.security.user.Permission.*;
//import static com.alibou.security.user.Role.ADMIN;
//import static com.alibou.security.user.Role.MANAGER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers("/api/v1/auth/**")
        .permitAll()
        .requestMatchers("/api/v1/admin/**").hasRole(Role.ADMIN.name())
        .requestMatchers("/api/v1/manager/**").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name())
        .requestMatchers("/api/v1/user/**").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name(),Role.USER.name())

//        .requestMatchers(HttpMethod.GET,"/api/v1/manager/**").hasAnyAuthority(ADMIN_READ.name(),MANAGER_READ.name())
//        .requestMatchers(HttpMethod.POST,"/api/v1/manager/**").hasAnyAuthority(ADMIN_CREATE.name(),MANAGER_CREATE.name())
//        .requestMatchers(HttpMethod.PUT,"/api/v1/manager/**").hasAnyAuthority(ADMIN_UPDATE.name(),MANAGER_UPDATE.name())
//        .requestMatchers(HttpMethod.DELETE,"/api/v1/manager/**").hasAnyAuthority(ADMIN_DELETE.name(),MANAGER_DELETE.name())

//        .requestMatchers(HttpMethod.GET,"/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
//        .requestMatchers(HttpMethod.POST,"/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
//        .requestMatchers(HttpMethod.PUT,"/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
//        .requestMatchers(HttpMethod.DELETE,"/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())

        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
  }
}
