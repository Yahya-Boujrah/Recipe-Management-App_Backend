package com.RecipeManagementApp_Backend.dto;


import com.RecipeManagementApp_Backend.entities.Category;
import com.RecipeManagementApp_Backend.entities.Ingredient;
import com.RecipeManagementApp_Backend.entities.Instruction;
import com.RecipeManagementApp_Backend.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeInput {

    private String id;

    private String title;

    private String description;

    private float rating;

    private String picture;

    private Category category;

    private User user;

    private Date createdAt;

    private List<Ingredient> ingredients;

    private List<Instruction> instructions;

}
