package com.RecipeManagementApp_Backend.controllers;

import com.RecipeManagementApp_Backend.dto.AuthenticationRequest;
import com.RecipeManagementApp_Backend.dto.AuthenticationResponse;
import com.RecipeManagementApp_Backend.entities.User;
import com.RecipeManagementApp_Backend.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    public final AuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request){
        System.out.println("inside controller");
        System.out.println(request);
        return ResponseEntity.ok(
                service.register(request)
        );
    }
}
