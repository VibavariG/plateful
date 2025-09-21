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
    private Unit fiberUnit;
    private BigDecimal proteinPerServing;
    private Unit proteinUnit;
    private BigDecimal caloriesPerServing;
    private BigDecimal totalSugarsPerServing;
    private Unit totalSugarsUnit;
    private BigDecimal addedSugarsPerServing;
    private Unit addedSugarsUnit;
    private BigDecimal totalFatPerServing;
    private Unit totalFatUnit;
    private BigDecimal transFatPerServing;
    private Unit transFatUnit;
    private BigDecimal saturatedFatPerServing;
    private Unit saturatedFatUnit;
    private BigDecimal carbsPerServing;
    private Unit carbsUnit;
    private BigDecimal cholestrolPerServing;
    private Unit cholestrolUnit;
    private BigDecimal sodiumPerServing;
    private Unit sodiumUnit;
    private BigDecimal vitDPerServing;
    private Unit vitDUnit;
    private BigDecimal calciumPerServing;
    private Unit calciumUnit;
    private BigDecimal ironPerServing;
    private Unit ironUnit;
    private BigDecimal potassiumPerServing;
    private Unit potassiumUnit;
    private Boolean isSkinSafe;
    private Boolean isGutSafe;
}

