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
    private Unit unit; // e.g., grams, ml, tsp
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

    private LocalDateTime createdAt;
}

