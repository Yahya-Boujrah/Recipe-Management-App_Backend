package com.RecipeManagementApp_Backend.controllers;

import com.RecipeManagementApp_Backend.entities.Recipe;
import com.RecipeManagementApp_Backend.services.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @SneakyThrows
    @PostMapping
    public ResponseEntity<?> index(@RequestBody Recipe recipe){

        return ResponseEntity.ok(
                recipeService.index(recipe)
        );

    }

}
