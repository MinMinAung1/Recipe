package com.hostmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.recipe.domain.Direction;

public interface DirectiornService {
	
	Direction saveDirection(Direction direction);
	
	Direction createDirection(Direction direction,Long recipeId);
	
	List<Direction> getAllDirectionByRecipe(Long recipeId);
	
	Optional<Direction> getDirectionById(Long directionId);
	
	void deleteDirectionById(Long directionId);

}
