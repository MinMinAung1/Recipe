package com.hostmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.recipe.domain.UnitOfMeasure;

public interface UomService {

	Optional<UnitOfMeasure> getUomById(Long uomId);

	Optional<UnitOfMeasure> getUomByName(String name);
	
	List<UnitOfMeasure> getAllUoms();

}
