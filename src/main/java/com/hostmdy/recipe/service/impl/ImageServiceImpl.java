package com.hostmdy.recipe.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	private final RecipeRepository recipeRepository;

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Override
	public void saveRecipeImageToDB(Long recipeId, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);

		if (recipeOpt.isEmpty()) {
			throw new NullPointerException("recipeId is not found in recipe table");
		}
		
		Recipe recipe = recipeOpt.get();

		Byte[] imageBytes = new Byte[imageFile.getBytes().length];
		
		int i = 0;
		for(final Byte b : imageFile.getBytes()) {
			imageBytes[i++] = b;
		}
		
		recipe.setImage(imageBytes);
		recipeRepository.save(recipe);

	}

}
