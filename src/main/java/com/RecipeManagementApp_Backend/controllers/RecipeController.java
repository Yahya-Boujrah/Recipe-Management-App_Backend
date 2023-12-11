package com.RecipeManagementApp_Backend.controllers;

import com.RecipeManagementApp_Backend.entities.Recipe;
import com.RecipeManagementApp_Backend.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor


@Controller
public class RecipeController {

    private final RecipeService recipeService;

//    @SneakyThrows
//    @PostMapping
//    public ResponseEntity<?> index(@RequestBody Recipe recipe){
//
//        return ResponseEntity.ok(
//                recipeService.index(recipe)
//        );
//
//    }

    @QueryMapping
    public Recipe recipeById(@Argument Integer id){
        return recipeService.getById(id);
    }

}
