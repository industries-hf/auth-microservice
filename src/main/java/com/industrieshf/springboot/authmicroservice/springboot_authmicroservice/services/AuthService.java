package com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.dto.UserDto;
import com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.dto.UserLoginDto;
import com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.models.User;
import com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.repositories.UserRepository;
import com.industrieshf.springboot.authmicroservice.springboot_authmicroservice.security.JwtService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;

@Service
public class AuthService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

     @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void registerUser(UserDto userDto) {

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setUserName(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        userRepository.save(user);

    }

    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));
    }

     // Método para login de usuario
     public String login(UserLoginDto userLoginDto) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtService.generateToken(userDetails.getUsername());
    }

}
