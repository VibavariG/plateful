package com.foodplanner.plateful.model.repository;

import com.foodplanner.plateful.model.entities.RecipeIngredient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface RecipeIngredientRepository extends ReactiveCrudRepository<RecipeIngredient, UUID> {

    // Find all ingredients for a recipe
    Flux<RecipeIngredient> findByRecipeId(UUID recipeId);

    // Find a specific ingredient for a recipe
    Mono<RecipeIngredient> findByRecipeIdAndIngredientId(UUID recipeId, UUID ingredientId);

    // Optional: delete all ingredients for a recipe not in a given list of ingredient IDs
    Flux<Void> deleteByRecipeIdAndIngredientIdNotIn(UUID recipeId, List<UUID> ingredientIds);
}
