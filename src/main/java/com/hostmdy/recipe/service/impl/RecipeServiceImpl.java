package com.hostmdy.recipe.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService{
	
	private final RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Recipe saveRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		return recipeRepository.save(recipe);
	}

	@Override
	public Recipe cretaeRecipe(Recipe recipe, Set<Category> categories) {
		// TODO Auto-generated method stub
		recipe.setCategories(categories);
		return saveRecipe(recipe);
	}

	@Override
	public List<Recipe> getAllRecipes() {
		// TODO Auto-generated method stub
		return (List<Recipe>) recipeRepository.findAll();
	}

	@Override
	public Optional<Recipe> getRecipeById(Long recipeId) {
		// TODO Auto-generated method stub
		return recipeRepository.findById(recipeId);
	}

	@Override
	public Optional<Recipe> getRecipeById(String name) {
		// TODO Auto-generated method stub
		return recipeRepository.findByName(name);
	}

	@Override
	public List<Recipe> getRecipesByReview(Double review) {
		// TODO Auto-generated method stub
		return recipeRepository.findByReview(review);
	}

	@Override
	public void deleteRecipeById(Long recipeId) {
		// TODO Auto-generated method stub
		recipeRepository.deleteById(recipeId);
		
	}


}
