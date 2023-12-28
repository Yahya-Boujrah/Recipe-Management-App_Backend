package com.RecipeManagementApp_Backend.repos;

import com.RecipeManagementApp_Backend.entities.Recipe;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepo extends ElasticsearchRepository<Recipe, String> {

    Optional<List<Recipe>> finByUserId(String id);
}
