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

	long recipeId;

	String recipeName;
	String userId;
	String userNickName;

	String image;
	String tip;
	String explanation;
	String category;

	List<RecipeFile> recipefile;

	List<StuffRecipe> stuffRecipe;

	List<RecipeProcedure> recipeProcedure;

}
