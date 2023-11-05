package com.hostmdy.recipe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.Recipe;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{
    
	List<Ingredient> findByRecipe(Recipe recipe);
}
