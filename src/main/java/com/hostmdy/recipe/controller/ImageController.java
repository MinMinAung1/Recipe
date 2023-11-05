package com.hostmdy.recipe.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.service.ImageService;
import com.hostmdy.recipe.service.RecipeService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/image")
public class ImageController {
	private final ImageService imageService;
	private final RecipeService recipeService;
	

	public ImageController(ImageService imageService, RecipeService recipeService) {
		super();
		this.imageService = imageService;
		this.recipeService = recipeService;
	}
	
	@GetMapping("/recipe/{recipeId}/upload")
	public String uploadRecipeImageForm(@PathVariable Long recipeId,Model model) {
		model.addAttribute("recipeId", recipeId);
		return "recipe/image-upload";
	}
	
	@PostMapping("/recipe/{recipeId}/upload")
	public String uploadRecipeImage(@PathVariable Long recipeId,@RequestParam MultipartFile imagefile) throws IOException {
		imageService.saveRecipeImageToDB(recipeId, imagefile);
		return "redirect:/recipe/"+recipeId+"/show";
	}
	
	@GetMapping("/recipe/{recipeId}/show")
	public void showRecipeIamge(@PathVariable Long recipeId,HttpServletResponse response) {
		Optional<Recipe> recipeOpt = recipeService.getRecipeById(recipeId);
		
		if(recipeOpt.isEmpty()) {
			throw new RuntimeException("recipe is not found!");
		}
		
		Recipe recipe = recipeOpt.get();
		
		byte[] imageBytes = new byte[recipe.getImage().length];
		
		int i = 0;
		for(final byte b : recipe.getImage()) {
			imageBytes[i++] = b;
		}
		
		InputStream ls = new ByteArrayInputStream(imageBytes);
		
		response.setContentType("image/jpeg");
		
		try {
			IOUtils.copy(ls, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

