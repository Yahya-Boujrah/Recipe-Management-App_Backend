package com.RecipeManagementApp_Backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;


@Document(indexName = "recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Text)
    private String picture;

    @Field(type = FieldType.Integer)
    private Integer rating = 0;

    @Field(type = FieldType.Object)
    private Category category;

    @Field(type = FieldType.Object)
    private User user;

    @Field(type = FieldType.Date)
    @CreatedDate
    private LocalDateTime createdAt;

    @Field(type = FieldType.Nested)
    private List<Ingredient> ingredients;

    @Field(type = FieldType.Nested)
    private List<Instruction> instructions;

}
