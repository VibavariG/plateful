package com.foodplanner.plateful.model.repository;

import com.foodplanner.plateful.model.entities.Recipe;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RecipeRepository extends ReactiveCrudRepository<Recipe, UUID> {

    // Find a recipe by name (case-insensitive)
    Mono<Recipe> findByNameIgnoreCase(String name);

    // Optional: check if a recipe exists by name
    Mono<Boolean> existsByNameIgnoreCase(String name);

    // Optional: delete a recipe by name
    Mono<Void> deleteByNameIgnoreCase(String name);
}
