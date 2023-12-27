package com.RecipeManagementApp_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeInput {

    private String id;

    private String title;

    private String description;

    private float rating;


//    private String picture;
//
//    private Category category;
//
//    private List<Ingredient> ingredients;
//
//    private List<Instruction> instructions;

}
