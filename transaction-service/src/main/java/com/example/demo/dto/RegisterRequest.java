package com.example.demo.dto;

import com.example.demo.Model.Gender;
import com.example.demo.Model.MaritalStatus;
import com.example.demo.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor

@NoArgsConstructor
public class
RegisterRequest {
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Role role;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDay;
  private Gender gender;
  private MaritalStatus maritalStatus;
  private String pinCode;
  private String residence;
  private String mobileNumber;
}
