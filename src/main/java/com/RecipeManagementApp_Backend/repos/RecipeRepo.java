package com.RecipeManagementApp_Backend.repos;

import com.RecipeManagementApp_Backend.entities.Recipe;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepo extends ElasticsearchRepository<Recipe, Long> {

}
