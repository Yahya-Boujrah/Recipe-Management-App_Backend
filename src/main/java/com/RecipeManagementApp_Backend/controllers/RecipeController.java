package com.RecipeManagementApp_Backend.controllers;

import com.RecipeManagementApp_Backend.dto.RecipeInput;
import com.RecipeManagementApp_Backend.entities.Recipe;
import com.RecipeManagementApp_Backend.entities.User;
import com.RecipeManagementApp_Backend.repos.UserRepo;
import com.RecipeManagementApp_Backend.services.RecipeService;
import com.RecipeManagementApp_Backend.services.SearchService;
import lombok.RequiredArgsConstructor;

import lombok.SneakyThrows;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    private final SearchService searchService;
    private final UserRepo userRepo;

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
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("email " + email);
//        User user = userRepo.findByEmail(email).orElseThrow();

        System.out.println(recipeInput);
        Recipe recipe = Recipe.builder()
                .id(recipeInput.getId())
                .title(recipeInput.getTitle())
                .description(recipeInput.getDescription())
                .rating(recipeInput.getRating())
                .picture(recipeInput.getPicture())
                .category(recipeInput.getCategory())
                .ingredients(recipeInput.getIngredients())
                .instructions(recipeInput.getInstructions())
//               .user(user)
                .createdAt(new Date())
                .picture(recipeInput.getPicture())
                .category(recipeInput.getCategory())
                .ingredients(recipeInput.getIngredients())
                .instructions(recipeInput.getInstructions())
                .build();
        System.out.println(recipe.getCreatedAt());
        recipeService.indexRecipe(recipe);
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
    public List<Recipe> searchRecipesInFields(@Argument String searchTerm/*, @Argument List<String> fields*/){
        List<String> fields = Arrays.asList("title", "description", "ingredients","instructions");

        return searchService.searchRecipesInFields(searchTerm, fields);
    }

    @SneakyThrows
    @QueryMapping
    public List<Recipe> sortRecipesByRating(@Argument float rating){
        return searchService.sortRecipesByRating(rating);
    }

    @SneakyThrows
    @QueryMapping
    public List<Recipe> recipesCreatedAfterDate(@Argument String date){
        return searchService.recipesCreatedAfterDate(date);
    }

    @SneakyThrows
    @QueryMapping
    public List<Recipe> sortRecipesByDate(){
        return searchService.sortRecipesByDate();
    }

    @SneakyThrows
    @QueryMapping
    public List<Recipe> top6RatedRecipes(){
        return searchService.top6RatedRecipes();
    }

    @SneakyThrows
    @QueryMapping
    public Recipe getLatestRecipe(){
        return searchService.getLatestRecipe();
    }

    @SneakyThrows
    @QueryMapping
    public List<Recipe> matchAllRecipesServices(){
        return searchService.matchAllRecipesServices();
    }

    @SneakyThrows
    @QueryMapping
    public List<Recipe> getUserRecipes(@Argument String id){
        return recipeService.getUserRecipes(id);
    }

    @SneakyThrows
    @QueryMapping
    public User getCurrentUser(){
        return recipeService.getCurrentUser();

    }

}
