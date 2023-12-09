package com.RecipeManagementApp_Backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import static jakarta.persistence.GenerationType.IDENTITY;

@Document(indexName = "instruction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instruction {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Field(type = FieldType.Keyword)
    private Long id;

    @Field(type = FieldType.Integer)
    private int number;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Object)
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;
}
