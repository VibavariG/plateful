package com.foodplanner.plateful.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RecipeWithIngredientDTO {
    private UUID id;
    private String name;
    private String description;
    private List<RecipeIngredientDTO> ingredients;
}

