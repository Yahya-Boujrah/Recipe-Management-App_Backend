package com.RecipeManagementApp_Backend.controllers;

import com.RecipeManagementApp_Backend.dto.RecipeInput;
import com.RecipeManagementApp_Backend.entities.Recipe;
import com.RecipeManagementApp_Backend.services.RecipeService;
import lombok.RequiredArgsConstructor;

import lombok.SneakyThrows;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    @SneakyThrows
    @QueryMapping
    public Recipe recipeById(@Argument String id){
        System.out.println("id -> " + id);
        return recipeService.findRecipeById(id);
    }

    @SneakyThrows
    @QueryMapping
    public List<Recipe> allRecipes(){
        return recipeService.findAllRecipes();
    }

    @SneakyThrows
    @MutationMapping
    public Recipe addRecipe(@Argument RecipeInput recipeInput){
        Recipe recipe = Recipe.builder()
                .id(recipeInput.getId())
                .title(recipeInput.getTitle())
                .description(recipeInput.getDescription())
                .build();
        recipeService.indexRecipe(recipe);
        System.out.println(recipe.getId());
        return recipeService.findRecipeById(recipe.getId());
    }

}
