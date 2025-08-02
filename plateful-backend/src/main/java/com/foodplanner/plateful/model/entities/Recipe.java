package com.foodplanner.plateful.model.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.foodplanner.plateful.utils.Constants.RECIPES;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(RECIPES)
public class Recipe {
    @Id
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}

