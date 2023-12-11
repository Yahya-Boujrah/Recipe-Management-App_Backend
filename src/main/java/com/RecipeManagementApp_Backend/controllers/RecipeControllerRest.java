package com.RecipeManagementApp_Backend.controllers;

import com.RecipeManagementApp_Backend.services.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recipe")
public class RecipeControllerRest {

    private final RecipeService recipeService;

    @SneakyThrows
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(
                recipeService.findAllRecipes()
        );
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id ) {
        return ResponseEntity.ok(
                recipeService.findRecipeById(id)
        );
    }

}
