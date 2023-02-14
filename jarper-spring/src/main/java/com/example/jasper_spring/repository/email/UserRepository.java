package com.example.jasper_spring.repository.email;

import com.example.jasper_spring.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByFirstName(String firstName);
    User findUserByEmail(String email);
}
