package com.example.springsecurity.demo.service;

import com.example.springsecurity.demo.dto.LoginDto;
import com.example.springsecurity.demo.dto.SingUpDto;
import com.example.springsecurity.demo.dto.UserDto;
import com.example.springsecurity.demo.entity.User;
import com.example.springsecurity.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public UserDto signUp(SingUpDto singUpDto) {
        Optional<User> user = userRepository.findByEmail(singUpDto.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with email already exists "+singUpDto.getEmail());
        }
        User toBeSavedUser = modelMapper.map(singUpDto, User.class);
        toBeSavedUser.setPassword(passwordEncoder.encode(toBeSavedUser.getPassword()));
        User savedUser = userRepository.save(toBeSavedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }
}
