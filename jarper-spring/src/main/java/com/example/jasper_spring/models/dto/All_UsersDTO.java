package com.example.jasper_spring.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class All_UsersDTO implements Serializable {
    private static final long serialVersionUID = 1447730407275844941L;
    @JsonProperty("User_List")
    List<UserDTO> users;
}
