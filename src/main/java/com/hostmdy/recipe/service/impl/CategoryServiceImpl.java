package com.hostmdy.recipe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.repository.CategoryRepository;
import com.hostmdy.recipe.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Optional<Category> getCategoryById(Long categoryId) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(categoryId);
	}

	@Override
	public Optional<Category> getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.findByName(name);
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return (List<Category>) categoryRepository.findAll();
	}

}
