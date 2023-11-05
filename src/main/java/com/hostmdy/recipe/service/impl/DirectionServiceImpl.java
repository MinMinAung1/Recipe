package com.hostmdy.recipe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.domain.Direction;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.repository.DirectionRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.service.DirectiornService;

@Service
public class DirectionServiceImpl implements DirectiornService {

	private final DirectionRepository directionRepository;
	private final RecipeRepository recipeRepository;

	public DirectionServiceImpl(DirectionRepository directionRepository, RecipeRepository recipeRepository) {
		super();
		this.directionRepository = directionRepository;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Direction saveDirection(Direction direction) {
		// TODO Auto-generated method stub
		return directionRepository.save(direction);
	}

	@Override
	public Direction createDirection(Direction direction, Long recipeId) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);

		if (recipeOpt.isEmpty()) {
			throw new NullPointerException("recipe is not found.");
		}

		Recipe recipe = recipeOpt.get();
		recipe.getDirections().add(direction);
		direction.setRecipe(recipe);
		return saveDirection(direction);
	}

	@Override
	public List<Direction> getAllDirectionByRecipe(Long recipeId) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);

		if (recipeOpt.isEmpty()) {
			throw new NullPointerException("recipe is not found.");
		}

		return directionRepository.findByRecipe(recipeOpt.get());
	}

	@Override
	public Optional<Direction> getDirectionById(Long directionId) {
		// TODO Auto-generated method stub
		return directionRepository.findById(directionId);
	}

	@Override
	public void deleteDirectionById(Long directionId) {
		// TODO Auto-generated method stub
		directionRepository.deleteById(directionId);

	}

}
