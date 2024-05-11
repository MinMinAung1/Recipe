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

import com.hostmdy.recipe.domain.Direction;
import com.hostmdy.recipe.service.DirectiornService;

@Controller
@RequestMapping("/direction")
public class DirectionController {

	private final DirectiornService directiornService;

	public DirectionController(DirectiornService directiornService) {
		super();
		this.directiornService = directiornService;
	}
	
	@GetMapping("/new/{recipeId}")
	public String createDirectionForm(@PathVariable Long recipeId,Model model) {
		List<Direction>  directionOpt = directiornService.getAllDirectionByRecipe(recipeId);
		
		if(directionOpt.isEmpty()) {
			throw new NullPointerException("direction is not found!");
		}
		
		model.addAttribute("recipeId", recipeId);
		model.addAttribute("direction", new Direction());
		
		return "direction/direction-form";
	}
	
	@PostMapping("/new")
	public String createDirection(@RequestParam Long recipeId,@ModelAttribute Direction direction) {
		directiornService.createDirection(direction, recipeId);
		return "redirect:/recipe/" + recipeId + "/directions";
	}
	
	@GetMapping("/{directionId}/update")
	public String updateDirection(@PathVariable Long directionId,Model model) {
		Optional<Direction> directionOpt = directiornService.getDirectionById(directionId);
		
		if(directionOpt.isEmpty()) {
			throw new NullPointerException("direction is null!");
		}
		
		Direction direction = directionOpt.get();
		
		model.addAttribute("recipeId", direction.getRecipe().getId());
		model.addAttribute("direction", direction);
		return "direction/direction-form";
	}
	
	@GetMapping("/{directionId}/delete/{recipeId}")
	public String deleteDirection(@PathVariable Long directionId,@PathVariable Long recipeId) {
		directiornService.deleteDirectionById(directionId);
		return "redirect:/recipe/" + recipeId + "/directions";
	}

}
