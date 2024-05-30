package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Repository.TokenRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UserTransfer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final RedisTemplate<String, UserTransfer> userRedisTemplate;
  private static final String USER_CACHE_PREFIX = "user::";

  public void adminRegister(RegisterRequest request) {
    User user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .maritalStatus(request.getMaritalStatus())
            .pinCode(request.getPinCode())
            .mobileNumber(request.getMobileNumber())
            .birthDay(request.getBirthDay())
            .residence(request.getResidence())
            .gender(request.getGender())
            .build();
    repository.save(user);
  }
  public UserTransfer getUser(Long userId) {
    Optional<User> user = repository.getUserById(userId);
    if (user.isPresent()){
      return UserTransfer.builder()
              .id(user.get().getId())
              .email(user.get().getEmail())
              .firstname(user.get().getFirstname())
              .lastname(user.get().getLastname())
              .password("No authorization")
              .role(user.get().getRole())
              .maritalStatus(user.get().getMaritalStatus())
              .pinCode(user.get().getPinCode())
              .mobileNumber(user.get().getMobileNumber())
              .birthDay(user.get().getBirthDay().toString())
              .residence(user.get().getResidence())
              .gender(user.get().getGender())
              .build();
    }
    else {
      throw new IllegalStateException("Invalid user id");
    }
  }
  public AuthenticationResponse register(RegisterRequest request) {
    if (request.getBirthDay() == null){
      request.setBirthDay(LocalDate.parse("1999-01-01"));
    }
    if (request.getGender() == null){
      request.setGender(Gender.MALE);
    }
    if (request.getMaritalStatus() == null){
      request.setMaritalStatus(MaritalStatus.SINGLE);
    }
    if (request.getPinCode() == null){
      request.setPinCode("1234");
    }
    if (request.getResidence() == null){
      request.setResidence("Egypt");
    }
    if (request.getMobileNumber() == null){
      request.setMobileNumber("01099999999");
    }
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .maritalStatus(request.getMaritalStatus())
        .pinCode(request.getPinCode())
        .mobileNumber(request.getMobileNumber())
        .birthDay(request.getBirthDay())
        .residence(request.getResidence())
        .gender(request.getGender())
        .build();

    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    //var refreshToken = jwtService.generateRefreshToken(user);
    var refreshToken = jwtToken;
    //saveUserToken(savedUser, jwtToken);
    UserTransfer userTransfer = UserTransfer.builder()
            .id(user.getId())
            .email(user.getEmail())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .password(user.getPassword())
            .role(user.getRole())
            .maritalStatus(user.getMaritalStatus())
            .pinCode(user.getPinCode())
            .mobileNumber(user.getMobileNumber())
            .birthDay(user.getBirthDay().toString())
            .residence(user.getResidence())
            .gender(user.getGender())
            .build();
    userRedisTemplate.opsForValue().set(USER_CACHE_PREFIX + jwtToken, userTransfer, 24, TimeUnit.HOURS);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    UserTransfer userTransfer = UserTransfer.builder()
            .id(user.getId())
            .email(user.getEmail())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .password(user.getPassword())
            .role(user.getRole())
            .maritalStatus(user.getMaritalStatus())
            .pinCode(user.getPinCode())
            .mobileNumber(user.getMobileNumber())
            .birthDay(user.getBirthDay().toString())
            .residence(user.getResidence())
            .gender(user.getGender())
            .build();
    userRedisTemplate.opsForValue().set(USER_CACHE_PREFIX + jwtToken, userTransfer, 24, TimeUnit.HOURS);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
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

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }

  public void deleteUserById(Long userId) {
    boolean exists = repository.existsById(userId);

    if (!exists) {
      throw new IllegalStateException("User with id "+ userId + " does not exist.");
    }
    repository.deleteById(userId);
  }
}
