package com.RecipeManagementApp_Backend.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "ingredient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {
    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

//    @Field(type = FieldType.Object)
//    @ManyToOne
//    @JoinColumn(name = "recipe_id", nullable = false)
//    private Recipe recipe;

}
