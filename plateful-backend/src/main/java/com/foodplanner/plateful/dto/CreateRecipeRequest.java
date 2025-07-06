package com.foodplanner.plateful.dto;

import com.foodplanner.plateful.model.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeRequest {
    private String name;
    private String description;

    private List<IngredientQuantity> ingredients;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IngredientQuantity {
        private String ingredientName;        // You select from existing ingredients
        private BigDecimal quantity;
        private Unit unitOverride;      // Optional
    }
}

