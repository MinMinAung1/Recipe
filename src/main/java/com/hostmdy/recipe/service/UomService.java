package com.hostmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.expression.spel.ast.OpInc;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.UnitOfMeasure;

public interface UomService {

	Optional<UnitOfMeasure> getUomById(Long uomId);

	Optional<UnitOfMeasure> getUomByName(String name);
	
	List<UnitOfMeasure> getAllUoms();

}
