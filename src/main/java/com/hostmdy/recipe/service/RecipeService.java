package com.hostmdy.recipe.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.Recipe;

public interface RecipeService {
	
	Recipe saveRecipe(Recipe recipe);
	
	Recipe cretaeRecipe(Recipe recipe,Set<Category> categories);
	
	List<Recipe> getAllRecipes();
	
	Optional<Recipe> getRecipeById(Long recipeId);
	
	Optional<Recipe> getRecipeById(String name);
	
	List<Recipe> getRecipesByReview(Double review);
	
	void deleteRecipeById(Long recipeId);

}
