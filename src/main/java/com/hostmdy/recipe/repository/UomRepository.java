package com.hostmdy.recipe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.domain.UnitOfMeasure;


public interface UomRepository extends CrudRepository<UnitOfMeasure, Long>{

	Optional<UnitOfMeasure> findByName(String name);
	
	List<UnitOfMeasure> findAllByOrderByName();
}
