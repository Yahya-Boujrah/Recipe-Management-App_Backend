package com.RecipeManagementApp_Backend.repos;

import com.RecipeManagementApp_Backend.entities.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends ElasticsearchRepository<Category,String> {
}
