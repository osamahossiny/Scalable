package com.example.demo.Repository;

import com.example.demo.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("select u from AppUser u where  u.email = ?1")
    Optional<AppUser> findUserByEmail(String email);

    //List<User> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);
}
