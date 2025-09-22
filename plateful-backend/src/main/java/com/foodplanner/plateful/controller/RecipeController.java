package com.foodplanner.plateful.controller;

import com.foodplanner.plateful.model.dto.CreateRecipeRequest;
import com.foodplanner.plateful.model.dto.RecipeIngredientDTO;
import com.foodplanner.plateful.model.dto.RecipeWithIngredientDTO;
import com.foodplanner.plateful.model.entities.Ingredient;
import com.foodplanner.plateful.model.entities.Recipe;
import com.foodplanner.plateful.model.entities.RecipeIngredient;
import com.foodplanner.plateful.model.repository.IngredientRepository;
import com.foodplanner.plateful.model.repository.RecipeIngredientRepository;
import com.foodplanner.plateful.model.repository.RecipeRepository;
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
        LocalDateTime now = LocalDateTime.now();
        Recipe recipe = new Recipe(
                null,
                request.getName(),
                request.getDescription(),
                now
        );

        return recipeRepo.save(recipe)
                .flatMap(savedRecipe ->
                        Flux.fromIterable(request.getIngredients())
                                .flatMap(iq ->
                                        ingredientRepo.findByNameIgnoreCase(iq.getIngredientName())
                                                .switchIfEmpty(Mono.error(new IllegalArgumentException("Ingredient not found: " + iq.getIngredientName())))
                                                .flatMap(ingredient -> {
                                                    RecipeIngredient ri = new RecipeIngredient(
                                                            null, // id is generated
                                                            savedRecipe.getId(), // link to recipe
                                                            ingredient.getId(),  // link to ingredient
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
                                            savedRecipe.getId(),
                                            savedRecipe.getName(),
                                            savedRecipe.getDescription(),
                                            ingredientDTOs
                                    );
                                })
                );
    }

    @PutMapping("/recipes/{id}")
    public Mono<RecipeWithIngredientDTO> updateRecipe(
            @PathVariable UUID id,
            @RequestBody CreateRecipeRequest request) {

        return recipeRepo.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Recipe not found: " + id)))
                .flatMap(recipe -> {
                    // Update description if provided
                    if (request.getDescription() != null) {
                        recipe.setDescription(request.getDescription());
                    }

                    return recipeRepo.save(recipe) // save updated recipe
                            .thenMany(Flux.fromIterable(request.getIngredients()))
                            .flatMap(iq ->
                                    ingredientRepo.findByNameIgnoreCase(iq.getIngredientName())
                                            .switchIfEmpty(Mono.error(new IllegalArgumentException(
                                                    "Ingredient not found: " + iq.getIngredientName())))
                                            .flatMap(ingredient ->
                                                    recipeIngredientRepo.findByRecipeIdAndIngredientId(id, ingredient.getId())
                                                            .flatMap(existingRI -> {
                                                                // Update existing quantity/unit
                                                                existingRI.setQuantity(iq.getQuantity());
                                                                if (iq.getUnitOverride() != null) {
                                                                    existingRI.setUnitOverride(iq.getUnitOverride());
                                                                }
                                                                return recipeIngredientRepo.save(existingRI)
                                                                        .map(savedRI -> new AbstractMap.SimpleEntry<>(savedRI, ingredient));
                                                            })
                                                            .switchIfEmpty(Mono.defer(() -> {
                                                                // Add new RecipeIngredient
                                                                RecipeIngredient ri = new RecipeIngredient(
                                                                        UUID.randomUUID(),
                                                                        id,
                                                                        ingredient.getId(),
                                                                        iq.getQuantity(),
                                                                        iq.getUnitOverride()
                                                                );
                                                                return recipeIngredientRepo.save(ri)
                                                                        .map(savedRI -> new AbstractMap.SimpleEntry<>(savedRI, ingredient));
                                                            }))
                                            )
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
                });
    }

    @GetMapping("/recipes/id")
    public Mono<UUID> getRecipeIdByName(@RequestParam String name) {
        return recipeRepo.findByNameIgnoreCase(name)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Recipe not found: " + name)))
                .map(Recipe::getId);
    }


}

