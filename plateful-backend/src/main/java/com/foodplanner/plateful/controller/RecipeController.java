package com.foodplanner.plateful.controller;

import com.foodplanner.plateful.dto.CreateRecipeRequest;
import com.foodplanner.plateful.dto.RecipeIngredientDTO;
import com.foodplanner.plateful.dto.RecipeWithIngredientDTO;
import com.foodplanner.plateful.model.Ingredient;
import com.foodplanner.plateful.model.Recipe;
import com.foodplanner.plateful.model.RecipeIngredient;
import com.foodplanner.plateful.repository.IngredientRepository;
import com.foodplanner.plateful.repository.RecipeIngredientRepository;
import com.foodplanner.plateful.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepo;
    private final RecipeIngredientRepository recipeIngredientRepo;
    private final IngredientRepository ingredientRepo;

    @GetMapping
    public Flux<RecipeWithIngredientDTO> getAllRecipes() {
        return recipeRepo.findAll()
                .flatMap(recipe -> recipeIngredientRepo.findByRecipeId(recipe.getId())
                        .flatMap(ri -> ingredientRepo.findById(ri.getIngredientId())
                                .map(ingredient -> new RecipeIngredientDTO(
                                        ingredient.getName(),
                                        ri.getQuantity(),
                                        ri.getUnitOverride() != null ? ri.getUnitOverride() : ingredient.getUnit()
                                ))
                        )
                        .collectList()
                        .map(ingredientDTOs -> new RecipeWithIngredientDTO(
                                recipe.getId(),
                                recipe.getName(),
                                recipe.getDescription(),
                                ingredientDTOs
                        ))
                );
    }

    @PostMapping
    public Mono<RecipeWithIngredientDTO> createRecipe(@RequestBody CreateRecipeRequest request) {
        UUID recipeId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        Recipe recipe = new Recipe(
                recipeId,
                request.getName(),
                request.getDescription(),
                now
        );

        return recipeRepo.save(recipe)
                .thenMany(Flux.fromIterable(request.getIngredients()))
                .flatMap(iq ->
                        ingredientRepo.findByNameIgnoreCase(iq.getIngredientName())
                                .switchIfEmpty(Mono.error(new IllegalArgumentException("Ingredient not found: " + iq.getIngredientName())))
                                .flatMap(ingredient -> {
                                    UUID recipeIngredientId = UUID.randomUUID();
                                    RecipeIngredient ri = new RecipeIngredient(
                                            recipeIngredientId,
                                            recipeId,
                                            ingredient.getId(),
                                            iq.getQuantity(),
                                            iq.getUnitOverride()
                                    );
                                    return recipeIngredientRepo.save(ri)
                                            .map(savedRI -> new AbstractMap.SimpleEntry<>(savedRI, ingredient));
                                })
                )
                .collectList()
                .map(savedPairs -> {
                    List<RecipeIngredientDTO> ingredientDTOs = savedPairs.stream()
                            .map(pair -> {
                                RecipeIngredient ri = pair.getKey();
                                Ingredient ing = pair.getValue();
                                return new RecipeIngredientDTO(
                                        ing.getName(),
                                        ri.getQuantity(),
                                        ri.getUnitOverride() != null ? ri.getUnitOverride() : ing.getUnit()
                                );
                            })
                            .toList();

                    return new RecipeWithIngredientDTO(
                            recipe.getId(),
                            recipe.getName(),
                            recipe.getDescription(),
                            ingredientDTOs
                    );
                });
    }
}

