package com.RecipeManagementApp_Backend.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.RecipeManagementApp_Backend.entities.Recipe;
import com.RecipeManagementApp_Backend.repos.RecipeRepo;
import com.RecipeManagementApp_Backend.entities.User;
import com.RecipeManagementApp_Backend.repos.UserRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private  final RecipeRepo recipeRepo;

    private final ElasticsearchClient elasticsearchClient;
    private final UserRepo userRepo;

    public String indexRecipe( Recipe recipe ) throws IOException {

        System.out.println(recipe);

        IndexResponse response= elasticsearchClient.index(i->i
                .index("recipe")
                .id(recipe.getId())
                .document(recipe));

        System.out.println( response.result().jsonValue());
        Map<String,String> responseMessages = Map.of(
                "Created","Document has been created",
                "Updated", "Document has been updated"
        );

        System.out.println(response);

        return responseMessages.getOrDefault(response.result().name(),"Error has occurred");
    }

    public Recipe findRecipeById(String recipeId) throws IOException {
        return elasticsearchClient.get(g->g.index("recipe")
                .id(recipeId),Recipe.class)
                .source();
    }

    public String deleteById(String recipeId) throws IOException {
        DeleteRequest deleteRequest = DeleteRequest.of(d->d.index("recipe").id(recipeId));
        DeleteResponse response = elasticsearchClient.delete(deleteRequest);

        return (response.result().name().equalsIgnoreCase("NOT_FOUND")
                ?"Document not found with id"+recipeId
                :"Document has been deleted");
    }

    public Iterable<Recipe> findAllRecipes() throws IOException {

//        SearchRequest request = SearchRequest.of(s->s.index("recipe"));
//        SearchResponse<Recipe> response = elasticsearchClient.search(request , Recipe.class);
//
//        return response.hits().hits()
//                .stream()
//                .map(Hit::source)
//                .collect(Collectors.toList());

        return recipeRepo.findAll();

    }

    public List<Recipe> getUserRecipes() {
        return recipeRepo.findByUserId("6e7f6913-8cd0-4bd0-a4d0-a8ee53522f7f").orElseThrow();
    }

    public User getCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("email of current user " + email);

        return userRepo.findByEmail(email).orElseThrow();
    }

}
