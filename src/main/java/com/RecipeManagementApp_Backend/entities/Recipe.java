package com.RecipeManagementApp_Backend.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;



@Document(indexName = "recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Text)
    private String picture;

//    @Field(type = FieldType.Object)
//    @ManyToOne
//    @JoinColumn(name = "category_id", nullable = false)
//    private Category category;
//
//    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Ingredient> ingredients;
//
//    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Instruction> instructions;
//
//    @Field(type = FieldType.Date)
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//
//    @Field(type = FieldType.Date)
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
}
