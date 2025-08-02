package com.foodplanner.plateful.model.repository;

import com.foodplanner.plateful.model.entities.RecipeIngredient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface RecipeIngredientRepository extends ReactiveCrudRepository<RecipeIngredient, UUID> {
    Flux<RecipeIngredient> findByRecipeId(UUID recipeId);
}
