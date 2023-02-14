package com.example.jasper_spring.service.user;

import com.example.jasper_spring.exception.UserNotFoundException;
import com.example.jasper_spring.models.dto.UserDTO;
import com.example.jasper_spring.models.user.User;
import com.example.jasper_spring.repository.email.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
           throw new UserNotFoundException("There is no users to be retrieved.");
        }
        return users;
    }

    @Override
    public User getUserByFirstName(String firstName) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User createUser(User user) {
         return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDTO userDTO, long userId) {
        return null;
    }

    @Override
    public void deleteUser(long userId) {

    }

    @Override
    public void deleteUserByEmail(String email) {

    }
}
