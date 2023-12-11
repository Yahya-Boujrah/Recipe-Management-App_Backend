package com.RecipeManagementApp_Backend.controllers;

import com.RecipeManagementApp_Backend.entities.Recipe;
import com.RecipeManagementApp_Backend.services.RecipeService;
import lombok.RequiredArgsConstructor;

import lombok.SneakyThrows;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class RecipeController {

    private final RecipeService recipeService;

//    @SneakyThrows
//    @PostMapping
//    public ResponseEntity<?> indexRecipe(@RequestBody Recipe recipe){
//        return ResponseEntity.ok(
//                recipeService.indexRecipe(recipe)
//        );
//    }
//    @SneakyThrows
//    @GetMapping
//    public ResponseEntity<?> findAll(){
//        return ResponseEntity.ok(
//                recipeService.findAll()
//        );
//    }
//    @SneakyThrows
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteById(@PathVariable String id ){
//        return ResponseEntity.ok(
//                recipeService.deleteById(id)
//        );
//    }
//
//    @SneakyThrows
//    @GetMapping("/{id}")
//    public ResponseEntity<?> findById(@PathVariable String id ) {
//        return ResponseEntity.ok(
//                recipeService.findById(id)
//        );
//    }

    @SneakyThrows
    @QueryMapping
    public Recipe recipeById(@Argument String id){
        System.out.println("idddddd ++++= " + id);
        return recipeService.findRecipeById(id);
    }

}
