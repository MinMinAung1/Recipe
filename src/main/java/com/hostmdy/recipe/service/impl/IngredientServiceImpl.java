package com.hostmdy.recipe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.domain.UnitOfMeasure;
import com.hostmdy.recipe.repository.IngredientRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.repository.UomRepository;
import com.hostmdy.recipe.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngredientRepository ingredientRepository;
	private final RecipeRepository recipeRepository;
	private final UomRepository uomRepository;

	public IngredientServiceImpl(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, UomRepository uomRepository) {
		super();
		this.ingredientRepository = ingredientRepository;
		this.recipeRepository = recipeRepository;
		this.uomRepository = uomRepository;
	}

	@Override
	public Ingredient saveIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return ingredientRepository.save(ingredient);
	}

	@Override
	public Ingredient createIngredient(Ingredient ingredient, Long recipeId,Long uomId) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);

		if (recipeOpt.isEmpty()) {
			throw new NullPointerException("recipeId is not found in recipe table");
		}
		
		Optional<UnitOfMeasure> uomOpt = uomRepository.findById(uomId);
		
		if(uomOpt.isEmpty()) {
			throw new NullPointerException("uom is not found!");
		}

		Recipe recipe = recipeOpt.get();
		ingredient.setRecipe(recipeOpt.get());
		recipe.getIngredients().add(ingredient);
		
		UnitOfMeasure uom = uomOpt.get();
		uom.getIngredients().add(ingredient);
		ingredient.setUom(uom);

		return saveIngredient(ingredient);
	}

	@Override
	public List<Ingredient> getAllIngredientsByRecipe(Long recipeId) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);

		if (recipeOpt.isEmpty()) {
			throw new NullPointerException("recipeId is not found in recipe table");
		}

		return ingredientRepository.findByRecipe(recipeOpt.get());
	}

	@Override
	public Optional<Ingredient> getIngredientById(Long ingredientId) {
		// TODO Auto-generated method stub
		return ingredientRepository.findById(ingredientId);
	}

	@Override
	public void deleteIngredientById(Long ingredientId) {
		// TODO Auto-generated method stub
		ingredientRepository.deleteById(ingredientId);

	}

}
