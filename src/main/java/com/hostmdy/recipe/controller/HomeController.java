package com.hostmdy.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hostmdy.recipe.service.RecipeService;

@Controller
public class HomeController {

	private final RecipeService recipeService;

	public HomeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@GetMapping({ "/", "/index", "/home" })
	public String showHome(Model model) {
		model.addAttribute("recipes", recipeService.getAllRecipes());
		return "index";
	}

}
