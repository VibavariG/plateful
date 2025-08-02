package com.foodplanner.plateful.model.repository;

import com.foodplanner.plateful.model.entities.Ingredient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, UUID> {
    Mono<Ingredient> findByNameIgnoreCase(String name);

}

