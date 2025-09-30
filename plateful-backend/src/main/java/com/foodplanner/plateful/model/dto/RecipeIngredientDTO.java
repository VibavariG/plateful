package com.foodplanner.plateful.model.dto;

import com.foodplanner.plateful.model.enums.Unit;
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

