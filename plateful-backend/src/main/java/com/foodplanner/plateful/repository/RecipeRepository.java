package com.foodplanner.plateful.repository;

import com.foodplanner.plateful.model.Recipe;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface RecipeRepository extends ReactiveCrudRepository<Recipe, UUID> {}



