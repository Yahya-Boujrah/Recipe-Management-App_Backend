package com.RecipeManagementApp_Backend.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instruction {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;


    @Field(type = FieldType.Integer)
    private int number;

    @Field(type = FieldType.Text)
    private String description;
}
