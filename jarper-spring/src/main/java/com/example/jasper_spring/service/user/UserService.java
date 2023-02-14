package com.example.jasper_spring.service.user;

import com.example.jasper_spring.models.dto.UserDTO;
import com.example.jasper_spring.models.user.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();
    User getUserByFirstName(String firstName);
    User getUserByEmail(String email);
    User createUser(UserDTO userDTO);
    User updateUser(UserDTO userDTO, long userId);
    void deleteUser(long userId);
    void deleteUserByEmail(String email);
}
