package com.RecipeManagementApp_Backend.dto;


import com.RecipeManagementApp_Backend.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor



public class AuthenticationResponse {

    private User user;

    private String token;

}