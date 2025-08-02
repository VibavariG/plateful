package com.foodplanner.plateful.controller;

import com.foodplanner.plateful.model.dto.CreateIngredientRequest;
import com.foodplanner.plateful.model.entities.Ingredient;
import com.foodplanner.plateful.model.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientRepository ingredientRepo;

    @PostMapping
    public Mono<Ingredient> createIngredient(@RequestBody CreateIngredientRequest request) {
        return ingredientRepo.findByNameIgnoreCase(request.getName())
                .flatMap(existing -> Mono.<Ingredient>error(new IllegalArgumentException("Ingredient already exists: " + request.getName())))
                .switchIfEmpty(
                        Mono.defer(() -> {
                            Ingredient newIngredient = new Ingredient(
                                    UUID.randomUUID(),
                                    request.getName(),
                                    request.getUnit(),
                                    request.getBrand(),
                                    request.getServingSize(),
                                    request.getFiberPerServing(),
                                    request.getProteinPerServing(),
                                    request.getCaloriesPerServing(),
                                    request.getTotalSugarsPerServing(),
                                            request.getAddedSugarsPerServing(),
                                    request.getTotalFatPerServing(),
                                            request.getTransFatPerServing(),
                                            request.getSaturatedFatPerServing(),
                                    request.getCarbsPerServing(),
                                            request.getCholestrolPerServing(),
                                            request.getSodiumPerServing(),
                                            request.getVitDPerServing(),
                                            request.getCalciumPerServing(),
                                            request.getIronPerServing(),
                                            request.getPotassiumPerServing(),
                                    request.getIsSkinSafe(),
                                    request.getIsGutSafe(),
                                    LocalDateTime.now());
                            return ingredientRepo.save(newIngredient);
                        })
                );
    }
}
