package com.hostmdy.recipe.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


public interface ImageService {
	void saveRecipeImageToDB(Long recipeId,MultipartFile imageFile) throws IOException;

}
