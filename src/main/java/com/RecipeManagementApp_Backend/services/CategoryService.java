package com.RecipeManagementApp_Backend.services;

import com.RecipeManagementApp_Backend.entities.Category;
import com.RecipeManagementApp_Backend.repos.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public Category index(Category category){
        return categoryRepo.save(category);
    }

    public Category findById(String id ){
        return categoryRepo.findById(id).orElseThrow();
    }
    public List<Category> findAll(){
        return (List<Category>) categoryRepo.findAll();
    }


}
