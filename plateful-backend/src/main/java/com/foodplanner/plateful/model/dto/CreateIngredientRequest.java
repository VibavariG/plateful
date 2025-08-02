package com.foodplanner.plateful.model.dto;

import com.foodplanner.plateful.model.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIngredientRequest {
    private String name;
    private Unit unit;
    private String brand;
    private Integer servingSize;
    private BigDecimal fiberPerServing;
    private BigDecimal proteinPerServing;
    private BigDecimal caloriesPerServing;
    private BigDecimal totalSugarsPerServing;
    private BigDecimal addedSugarsPerServing;
    private BigDecimal totalFatPerServing;
    private BigDecimal transFatPerServing;
    private BigDecimal saturatedFatPerServing;
    private BigDecimal carbsPerServing;
    private BigDecimal cholestrolPerServing;
    private BigDecimal sodiumPerServing;
    private BigDecimal vitDPerServing;
    private BigDecimal calciumPerServing;
    private BigDecimal ironPerServing;
    private BigDecimal potassiumPerServing;
    private Boolean isSkinSafe;
    private Boolean isGutSafe;
}

