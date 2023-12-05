package com.RecipeManagementApp_Backend.entities;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instruction {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private int number;
    private String description;
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;
}
