package com.hostmdy.recipe.booststrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.Difficulty;
import com.hostmdy.recipe.domain.Direction;
import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.Note;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.domain.UnitOfMeasure;
import com.hostmdy.recipe.repository.CategoryRepository;
import com.hostmdy.recipe.repository.DirectionRepository;
import com.hostmdy.recipe.repository.IngredientRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.repository.UomRepository;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	public RecipeRepository recipeRepository;

	@Autowired
	public IngredientRepository ingredientRepository;

	@Autowired
	public DirectionRepository directionRepository;

	@Autowired
	public CategoryRepository categoryRepository;

	@Autowired
	public UomRepository uomRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		recipeRepository.saveAll(createRecipe());
	}

	private List<Recipe> createRecipe() {

		// category
		Optional<Category> americanOpt = categoryRepository.findByName("American");

		if (americanOpt.isEmpty()) {
			throw new NullPointerException("American category is not found.");
		}

		Optional<Category> fastFoodOpt = categoryRepository.findByName("FastFood");

		if (fastFoodOpt.isEmpty()) {
			throw new NullPointerException("FastFood category is not found.");
		}

		// Units
		Optional<UnitOfMeasure> wholeOpt = uomRepository.findByName("whole");

		if (wholeOpt.isEmpty()) {
			throw new NullPointerException("whole units is not found.");
		}

		Optional<UnitOfMeasure> teaspoonOpt = uomRepository.findByName("teaspoon");

		if (teaspoonOpt.isEmpty()) {
			throw new NullPointerException("teaspoon units is not found.");
		}

		Optional<UnitOfMeasure> poundOpt = uomRepository.findByName("pound");

		if (poundOpt.isEmpty()) {
			throw new NullPointerException("pound units is not found.");
		}

		Optional<UnitOfMeasure> cupOpt = uomRepository.findByName("cup");

		if (cupOpt.isEmpty()) {
			throw new NullPointerException("cup units is not found.");
		}

		// set category
		Category american = americanOpt.get();
		Category fastfood = fastFoodOpt.get();

		// set UnitOfMeasure
		UnitOfMeasure whole = wholeOpt.get();
		UnitOfMeasure teaspoon = teaspoonOpt.get();
		UnitOfMeasure pound = poundOpt.get();
		UnitOfMeasure cup = cupOpt.get();

		Recipe recipe = new Recipe();
		recipe.setName("The Perfect Basic Burger");
		recipe.setDescription(
				"These burger patties are made with ground beef and an easy bread crumb mixture. Nothing beats a simple hamburger on a warm summer evening. Pile these burgers with your favorite condiments and pop open a cool drink!");
		recipe.setReview(4.4);
		recipe.setPrepTime(5);
		recipe.setCookTime(15);
		recipe.setTotalTime(20);
		recipe.setServings(4);
		recipe.setDifficulty(Difficulty.EASY);
		recipe.setSource("https://www.allrecipes.com/recipe/25473/the-perfect-basic-burger/");

		Note note = new Note();
		note.setNote("How to Store Beef Burgers\r\n"
				+ "Cooked beef burgers can be safely refrigerated for three to four days. Make sure to promptly chill after cooking in an airtight container to lengthen shelf life.");

		// recipe - note
		recipe.setNote(note);

		// recipe - category
		recipe.getCategories().add(american);
		recipe.getCategories().add(fastfood);

		american.getRecipes().add(recipe);
		fastfood.getRecipes().add(recipe);
		
		//ingredients
		Ingredient egg = new Ingredient("large egg", 1, new BigDecimal(1), recipe, whole);
		Ingredient salt = new Ingredient("salt", 2, new BigDecimal(0.5), recipe, teaspoon);
		Ingredient pepper = new Ingredient("ground black pepper", 3, new BigDecimal(0.5), recipe, teaspoon);
		Ingredient beef = new Ingredient("ground beef", 4, new BigDecimal(1), recipe, pound);
		Ingredient crumbs = new Ingredient("fine dry bread crumbs", 5, new BigDecimal(0.5), recipe, cup);
		
		//recipe - ingredients
		recipe.getIngredients().add(egg);
		recipe.getIngredients().add(salt);
		recipe.getIngredients().add(pepper);
		recipe.getIngredients().add(beef);
		recipe.getIngredients().add(crumbs);
		
		//directions
		Direction step1 = new Direction("Preheat an outdoor grill for high heat and lightly oil grate.", 1, recipe);
		Direction step2 = new Direction("Whisk together egg, salt, and pepper in a medium bowl.", 2, recipe);
		Direction step3 = new Direction("Add ground beef and bread crumbs and mix with your hands or a fork until well blended.", 3, recipe);
		Direction step4 = new Direction("Form into four 3/4-inch-thick patties.", 4, recipe);
		Direction step5 = new Direction("Place patties on the preheated grill. Cover and cook 6 to 8 minutes per side, or to desired doneness. An instant-read thermometer inserted into the center should read at least 160 degrees F (70 degrees C).", 5, recipe);
		Direction step6 = new Direction("Serve hot and enjoy!", 6, recipe);
		
		//recipe - directions
		recipe.getDirections().add(step1);
		recipe.getDirections().add(step2);
		recipe.getDirections().add(step3);
		recipe.getDirections().add(step4);
		recipe.getDirections().add(step5);
		recipe.getDirections().add(step6);
		
				
//		recipeRepository.save(recipe);
	    //save recipe
		List<Recipe> recipes = new ArrayList<>();
		recipes.add(recipe);

		return recipes;
	}

}
