package com.RecipeManagementApp_Backend.controllers;

import com.RecipeManagementApp_Backend.entities.Recipe;
import com.RecipeManagementApp_Backend.services.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @SneakyThrows
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(
                recipeService.findAll()
        );
    }
    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id ){
        return ResponseEntity.ok(
                recipeService.deleteById(id)
        );
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id ){
        return ResponseEntity.ok(
                recipeService.findById(id)
        );
    }

}
