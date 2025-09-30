package com.foodplanner.plateful.model.entities;

import com.foodplanner.plateful.model.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Table("recipe_ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredient {
    @Id
    private UUID id;
    private UUID recipeId;
    private UUID ingredientId;
    private BigDecimal quantity;   // e.g., 50.0
    private Unit unitOverride;   // e.g., tsp (optional)
}

