package com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.dto.UserDto;
import com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.dto.UserLoginDto;
import com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/api/v1/auth/register")
  public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
    authService.registerUser(userDto);
    return ResponseEntity.ok("User registered successfully");
  }

  @PostMapping("/api/v1/auth/login")
  public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDTO) {
    String jwt = authService.login(userLoginDTO);
    return ResponseEntity.ok(jwt);
  }
}
