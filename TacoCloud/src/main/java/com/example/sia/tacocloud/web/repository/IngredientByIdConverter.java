package com.example.sia.tacocloud.web.repository;

import com.example.sia.tacocloud.web.pojo.Ingredient;
import com.example.sia.tacocloud.web.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Coulomb
 * @since 2021/11/4 17:45
 */
@Slf4j
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String ingredient_id) {
        log.info("调用了ingredient的converter");
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredient_id);
        return optionalIngredient.orElse(null);
    }
}
