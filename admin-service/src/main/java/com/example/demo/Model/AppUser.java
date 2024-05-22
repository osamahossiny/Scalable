package com.example.demo.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(
        name = "app_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email")
        }
)
public class AppUser {
    @Id
    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "app_user_sequence"
    )
    private Long id;
    @Column(
            name = "user_name",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String userName;
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
            columnDefinition = ""
    )
    private String password;
    @Column(
            name = "birthday",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private LocalDate birthDay;
    @Transient
    private int age;
    @Column(
            name = "gender",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String gender;
    @Column(
            name = "marital_status",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String maritalStatus;
    @Column(
            name = "pin_code",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String pinCode;
    @Column(
            name = "residence",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String residence;
    @Column(
            name = "mobile_number",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String mobileNumber;
    @Column(
            name = "first_name",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false,
            updatable = true,
            columnDefinition = "TEXT"
    )
    private String lastName;

    public AppUser(Long id, String userName, String email, String password, LocalDate birthDay, String gender, String maritalStatus, String pinCode, String residence, String mobileNumber, String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.pinCode = pinCode;
        this.residence = residence;
        this.mobileNumber = mobileNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AppUser() {
    }

    public AppUser(String userName, String password, String email, LocalDate birthDay, String gender, String maritalStatus, String pinCode, String residence, String mobileNumber, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.birthDay = birthDay;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.pinCode = pinCode;
        this.residence = residence;
        this.mobileNumber = mobileNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getAge() {
        return Period.between(this.birthDay, LocalDate.now()).getYears();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", residence='" + residence + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
