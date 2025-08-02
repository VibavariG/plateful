package com.foodplanner.plateful.model.repository;

import com.foodplanner.plateful.model.entities.Recipe;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface RecipeRepository extends ReactiveCrudRepository<Recipe, UUID> {}



