package com.foodplanner.plateful.model.entities;

import com.foodplanner.plateful.model.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.foodplanner.plateful.utils.Constants.INGREDIENTS;

@Table(INGREDIENTS)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    private UUID id;

    private String name;
    private Unit unit; // base unit for serving size
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

    private LocalDateTime createdAt;
}