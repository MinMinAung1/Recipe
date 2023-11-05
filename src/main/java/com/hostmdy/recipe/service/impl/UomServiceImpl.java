package com.hostmdy.recipe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.domain.UnitOfMeasure;
import com.hostmdy.recipe.repository.UomRepository;
import com.hostmdy.recipe.service.UomService;

@Service
public class UomServiceImpl implements UomService {

	private final UomRepository uomRepository;

	public UomServiceImpl(UomRepository uomRepository) {
		super();
		this.uomRepository = uomRepository;
	}

	@Override
	public Optional<UnitOfMeasure> getUomById(Long uomId) {
		// TODO Auto-generated method stub
		return uomRepository.findById(uomId);
	}

	@Override
	public Optional<UnitOfMeasure> getUomByName(String name) {
		// TODO Auto-generated method stub
		return uomRepository.findByName(name);
	}

	@Override
	public List<UnitOfMeasure> getAllUoms() {
		// TODO Auto-generated method stub
		return uomRepository.findAllByOrderByName();
	}

}
