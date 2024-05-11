package com.hostmdy.recipe.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.Note;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.service.CategoryService;
import com.hostmdy.recipe.service.RecipeService;

@Controller
@RequestMapping("/recipe") // base mapping (should base mapping each controller)
public class RecipeController {

	private final RecipeService recipeService;
	private final CategoryService categoryService;

	public RecipeController(RecipeService recipeService, CategoryService categoryService) {
		super();
		this.recipeService = recipeService;
		this.categoryService = categoryService;
	}

	@GetMapping("/{recipeId}/show")
	public String showRecipeDetails(@PathVariable Long recipeId, Model model) {
		Optional<Recipe> recipeOpt = recipeService.getRecipeById(recipeId);

		if (recipeOpt.isEmpty()) {
			throw new NullPointerException("recipe is not found.");
		}

		model.addAttribute("recipe", recipeOpt.get());
		return "recipe/recipe-details";
	}

	@GetMapping("/new")
	public String createRecipeForm(Model model) {
		List<Category> categories = categoryService.getAllCategories();

		if (categories.isEmpty()) {
			throw new NullPointerException("categories are empty!");
		}

		Recipe recipe = new Recipe();
		recipe.setNote(new Note());
		model.addAttribute("recipe", recipe);
		model.addAttribute("categories", categories);
		return "recipe/add-recipe";

	}

	@PostMapping("/new")
	public String createRecipe(@ModelAttribute Recipe recipe, @RequestParam String[] categoryNames) {
		Set<Category> categories = new HashSet<>();

		for (final String categoryName : categoryNames) {
			Optional<Category> categoryOpt = categoryService.getCategoryByName(categoryName);

			if (categoryOpt.isEmpty()) {
				throw new NullPointerException("category is not found.");
			}
			categories.add(categoryOpt.get());
		}

		Recipe createdRecipe = recipeService.cretaeRecipe(recipe, categories);
		System.out.println("### Recipe with id = " + createdRecipe.getId() + " is created ###");
		return "redirect:/";
	}

	@GetMapping("/{recipeId}/update")
	public String updateRecipeForm(@PathVariable Long recipeId, Model model) {
		List<Category> categories = categoryService.getAllCategories();

		if (categories.isEmpty()) {
			throw new NullPointerException("categories are empty!");
		}

		Optional<Recipe> recipeOpt = recipeService.getRecipeById(recipeId);

		if (recipeOpt.isEmpty()) {
			throw new NullPointerException("recipe is not found!");
		}

		model.addAttribute("recipe", recipeOpt.get());
		model.addAttribute("categories", categories);
		return "recipe/add-recipe";
	}

	@GetMapping("/{recipeId}/delete")
	public String deleteRecipe(@PathVariable Long recipeId) {
		recipeService.deleteRecipeById(recipeId);

		return "redirect:/";
	}

	@GetMapping("/{recipeId}/ingredients")
	public String showIngredients(@PathVariable Long recipeId, Model model) {
		Optional<Recipe> recipeOpt = recipeService.getRecipeById(recipeId);

		if (recipeOpt.isEmpty()) {
			throw new NullPointerException("recipe is null!");
		}

		Recipe recipe = recipeOpt.get();
		model.addAttribute("ingredients", recipe.getIngredients());
		model.addAttribute("recipeId", recipeId);

		return "ingredient/show";
	}

	@GetMapping("/{recipeId}/directions")
	public String showDirection(@PathVariable Long recipeId, Model model) {
		Optional<Recipe> recipeOpt = recipeService.getRecipeById(recipeId);

		if (recipeOpt.isEmpty()) {
			throw new NullPointerException("recipe is null!");
		}
		
		Recipe recipe = recipeOpt.get();
		model.addAttribute("directions", recipe.getDirections());
		model.addAttribute("recipeId", recipeId);
		
		
		return "direction/show";
	}
}
