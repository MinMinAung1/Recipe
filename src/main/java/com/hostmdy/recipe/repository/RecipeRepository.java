package com.hostmdy.recipe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.domain.Recipe;


public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    
	Optional<Recipe> findByName(String name);
	
	List<Recipe> findByReview(Double review);
}
