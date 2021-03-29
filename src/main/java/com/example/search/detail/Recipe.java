package com.example.search.detail;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

	private long recipeId;

	private String recipeName;
	private long userId;

	private String image;
	private String tip;
	private String Explanation;
	private String category;

	private List<RecipeFile> Recipefile;

	private List<StuffRecipe> StuffRecipe;

	private List<RecipeProcedure> recipeProcedure;

}
