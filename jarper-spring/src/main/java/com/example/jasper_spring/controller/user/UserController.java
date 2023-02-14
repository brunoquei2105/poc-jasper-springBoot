package com.example.jasper_spring.controller.user;

import com.example.jasper_spring.models.dto.All_UsersDTO;
import com.example.jasper_spring.models.dto.UserDTO;
import com.example.jasper_spring.models.user.User;
import com.example.jasper_spring.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1.0/user")
@Tag(name = "Users API",
        description ="This is the API for User Model." )
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Operation(
            responses = {@ApiResponse(description = "Get all Users models from Database", responseCode = "200")},
            summary = "",
            tags = {}
    )
    @GetMapping(value = "/get-all-users")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<All_UsersDTO> getAllUsers(){

        All_UsersDTO usersDTO = new All_UsersDTO();
        try {

            List<User> users = userService.getAllUser();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            List<UserDTO> dtos = users.stream().map( user -> modelMapper.map(user, UserDTO.class)).toList();
            usersDTO.setUsers(dtos);

        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no users found", e.getCause());
        }
         return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }
    @Operation(
            responses = {@ApiResponse(description = "Create and save a User.", responseCode = "201")},
            summary = "",
            tags = {}
    )
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> postUser(@RequestBody @Valid UserDTO userDTO){
        User user ;
        try {

            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            user = modelMapper.map(userDTO, User.class);
            userService.createUser(user);

        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create a user", e.getCause());
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
