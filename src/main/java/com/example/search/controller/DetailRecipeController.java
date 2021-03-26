package com.example.search.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.search.detail.Recipe;

@RestController
public class DetailRecipeController {

	@GetMapping(value = "/recipe/detail{id}")
	public Recipe getDetailRecipe(@PathVariable long id) {
		return null;
	}
}
