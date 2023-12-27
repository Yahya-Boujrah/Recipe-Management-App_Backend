package com.RecipeManagementApp_Backend.controllers;

import com.RecipeManagementApp_Backend.dto.RecipeInput;
import com.RecipeManagementApp_Backend.entities.Recipe;
import com.RecipeManagementApp_Backend.services.RecipeService;
import com.RecipeManagementApp_Backend.services.SearchService;
import lombok.RequiredArgsConstructor;

import lombok.SneakyThrows;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    private final SearchService searchService;

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
                .rating(recipeInput.getRating())
                .createdAt(new Date())
                .build();
        System.out.println(recipe.getCreatedAt());
        recipeService.indexRecipe(recipe);
        System.out.println(recipe.getId());
        return recipeService.findRecipeById(recipe.getId());
    }

    @SneakyThrows
    @MutationMapping
    public String deleteRecipe(@Argument String id){
        return recipeService.deleteById(id);
    }

    @SneakyThrows
    @QueryMapping
    public List<Recipe> recipesByCategory(@Argument String category){
        return searchService.recipesByCategory(category);
    }
    @SneakyThrows
    @QueryMapping
    public List<Recipe> recipesFuzzySearch(@Argument String title){
        return searchService.recipesFuzzySearch(title);
    }

    @SneakyThrows
    @QueryMapping
    public List<Recipe> searchRecipesInFields(@Argument String searchTerm, @Argument List<String> fields){
        return searchService.searchRecipesInFields(searchTerm, fields);
    }

    @SneakyThrows
    @QueryMapping
    public List<Recipe> recipesWithHigherRating(@Argument float rating){
        return searchService.recipesWithHigherRating(rating);
    }

    @SneakyThrows
    @QueryMapping
    public List<Recipe> recipesCreatedAfterDate(@Argument String date){
        return searchService.recipesCreatedAfterDate(date);
    }

    @SneakyThrows
    @QueryMapping
    public List<Recipe> top6RatedRecipes(){
        return searchService.top6RatedRecipes();
    }
}
