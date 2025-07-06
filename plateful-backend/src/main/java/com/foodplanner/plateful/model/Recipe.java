package com.foodplanner.plateful.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("recipes")
public class Recipe {
    @Id
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}

