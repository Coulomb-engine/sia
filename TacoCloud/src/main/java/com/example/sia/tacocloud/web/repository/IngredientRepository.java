package com.example.sia.tacocloud.web.repository;

import com.example.sia.tacocloud.web.pojo.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Coulomb
 * @since 2021/10/22 17:16
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

//    Iterable<Ingredient> findAll();
//
//    Ingredient findOne(String id);
//
//    Ingredient save(Ingredient ingredient);
}
