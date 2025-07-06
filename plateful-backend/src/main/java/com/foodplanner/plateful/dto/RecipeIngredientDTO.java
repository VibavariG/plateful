package com.foodplanner.plateful.dto;

import com.foodplanner.plateful.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RecipeIngredientDTO {
    private String ingredientName;
    private BigDecimal quantity;
    private Unit unit;
}

