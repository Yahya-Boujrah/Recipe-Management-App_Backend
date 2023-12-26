package com.RecipeManagementApp_Backend.repos;

import com.RecipeManagementApp_Backend.entities.Recipe;
import com.RecipeManagementApp_Backend.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface UserRepo extends ElasticsearchRepository<User, String> {
    Optional<User> findByEmail(String email);
}
