package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_unique", columnNames = "email")
        }
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails, Serializable {

  @Id
  @GeneratedValue
  private Long id;
  @Column(
          name = "first_name",
          nullable = false,
          updatable = true,
          columnDefinition = "TEXT"
  )
  private String firstname;
  @Column(
          name = "last_name",
          nullable = false,
          updatable = true,
          columnDefinition = "TEXT"
  )
  private String lastname;
  @Column(
          name = "email",
          nullable = false,
          updatable = true,
          columnDefinition = "TEXT"
  )
  private String email;
  @Column(
          name = "password",
          nullable = false,
          updatable = true,
          columnDefinition = "TEXT"
  )
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

//  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//  private List<Token> tokens;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDay;
  @Transient
  private int age;
  private Gender gender;
  private MaritalStatus maritalStatus;
  private String pinCode;
  private String residence;
  private String mobileNumber;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public int getAge() {
    return Period.between(this.birthDay, LocalDate.now()).getYears();
  }
}
