package com.hostmdy.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.UnitOfMeasure;
import com.hostmdy.recipe.service.IngredientService;
import com.hostmdy.recipe.service.UomService;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

	private final IngredientService ingredientService;
	private final UomService uomService;

	public IngredientController(IngredientService ingredientService, UomService uomService) {
		super();
		this.ingredientService = ingredientService;
		this.uomService = uomService;
	}

	@GetMapping("/new/{recipeId}")
	public String createIngredientForm(@PathVariable Long recipeId, Model model) {
		List<UnitOfMeasure> uomList = uomService.getAllUoms();

		if (uomList.isEmpty()) {
			throw new NullPointerException("uomList is empty!");
		}

		model.addAttribute("recipeId", recipeId);
		model.addAttribute("uomList", uomList);
		model.addAttribute("ingredient", new Ingredient());

		return "ingredient/ingredient-form";

	}

	@PostMapping("/new")
	public String createIngredient(@RequestParam Long uomId, @RequestParam Long recipeId,
			@ModelAttribute Ingredient ingredient) {
		Ingredient creratedIngredient = ingredientService.createIngredient(ingredient, recipeId, uomId);
		System.out.println("### ingredient with id" + creratedIngredient.getId() + " is created ###");
		return "redirect:/recipe/" + recipeId + "/ingredients";

	}

	@GetMapping("/{ingredientId}/update")
	public String updateIngredientForm(@PathVariable Long ingredientId, Model model) {
		Optional<Ingredient> ingredientOpt = ingredientService.getIngredientById(ingredientId);
		if (ingredientOpt.isEmpty()) {
			throw new NullPointerException("ingredient is not found!");
		}

		List<UnitOfMeasure> uomList = uomService.getAllUoms();

		if (uomList.isEmpty()) {
			throw new NullPointerException("Uom is not found!");
		}

		Ingredient ingredient = ingredientOpt.get();

		model.addAttribute("recipeId", ingredient.getRecipe().getId());
		model.addAttribute("uomList", uomList);
		model.addAttribute("ingredient", ingredient);

		return "ingredient/ingredient-form";
	}

	@GetMapping("/{ingredientId}/delete/{recipeId}")
	public String deleteIngredient(@PathVariable Long ingredientId, @PathVariable Long recipeId) {
		ingredientService.deleteIngredientById(ingredientId);
		return "redirect:/recipe/" + recipeId + "/ingredients";
	}

}
