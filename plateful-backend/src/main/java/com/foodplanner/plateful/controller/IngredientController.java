package com.foodplanner.plateful.controller;

import com.foodplanner.plateful.model.dto.CreateIngredientRequest;
import com.foodplanner.plateful.model.entities.Ingredient;
import com.foodplanner.plateful.model.entities.Recipe;
import com.foodplanner.plateful.model.repository.IngredientRepository;
import io.r2dbc.spi.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientRepository ingredientRepo;

    @PostMapping
    public Mono<Void> createIngredient(@RequestBody CreateIngredientRequest request) {
        return ingredientRepo.findByNameIgnoreCase(request.getName())
                .flatMap(existing -> Mono.error(
                        new IllegalArgumentException("Ingredient already exists: " + request.getName())))
                .switchIfEmpty(
                        ingredientRepo.save(new Ingredient(
                                null,
                                request.getName(),
                                request.getUnit(),
                                request.getBrand(),
                                request.getServingSize(),
                                request.getFiberPerServing(),
                                request.getFiberUnit(),
                                request.getProteinPerServing(),
                                request.getProteinUnit(),
                                request.getCaloriesPerServing(),
                                request.getTotalSugarsPerServing(),
                                request.getTotalSugarsUnit(),
                                request.getAddedSugarsPerServing(),
                                request.getAddedSugarsUnit(),
                                request.getTotalFatPerServing(),
                                request.getTotalFatUnit(),
                                request.getTransFatPerServing(),
                                request.getTransFatUnit(),
                                request.getSaturatedFatPerServing(),
                                request.getSaturatedFatUnit(),
                                request.getCarbsPerServing(),
                                request.getCarbsUnit(),
                                request.getCholestrolPerServing(),
                                request.getCholestrolUnit(),
                                request.getSodiumPerServing(),
                                request.getSodiumUnit(),
                                request.getVitDPerServing(),
                                request.getVitDUnit(),
                                request.getCalciumPerServing(),
                                request.getCalciumUnit(),
                                request.getIronPerServing(),
                                request.getIronUnit(),
                                request.getPotassiumPerServing(),
                                request.getPotassiumUnit(),
                                request.getIsSkinSafe(),
                                request.getIsGutSafe(),
                                LocalDateTime.now()
                        ))
                ).then();
    }

    @GetMapping
    public Flux<Ingredient> getAllIngredients() {
        return ingredientRepo.findAll();
    }

    @GetMapping("/names")
    public Mono<Map<String, List<String>>> getAllIngredientNames() {
        return ingredientRepo.findAll()
                .map(Ingredient::getName)
                .collectList()
                .map(names -> Map.of("data", names));
    }

    @GetMapping("/id")
    public Mono<UUID> getIngedientIdByName(@RequestParam String name) {
        return ingredientRepo.findByNameIgnoreCase(name)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Ingredient not found: " + name)))
                .map(Ingredient::getId);
    }

}
