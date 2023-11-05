package com.hostmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.recipe.domain.Ingredient;

public interface IngredientService{
	
	Ingredient saveIngredient(Ingredient ingredient);
	
	Ingredient createIngredient(Ingredient ingredient,Long recipeId,Long uomId);
	
	List<Ingredient> getAllIngredientsByRecipe(Long recipeId);
	
	Optional<Ingredient> getIngredientById(Long ingredientId);
	
	void deleteIngredientById(Long ingredientId);

}
