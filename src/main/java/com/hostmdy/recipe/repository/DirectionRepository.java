package com.hostmdy.recipe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.domain.Direction;
import com.hostmdy.recipe.domain.Recipe;

public interface DirectionRepository extends CrudRepository<Direction, Long>{

	List<Direction> findByRecipe(Recipe recipe);
}
