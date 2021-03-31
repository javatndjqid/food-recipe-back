package com.example.search.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.search.detail.Recipe;
import com.example.search.detail.StuffRecipe;
import com.example.search.entity.Category;
import com.example.search.entity.SearchRecipe;
import com.example.search.repository.CategoryRepository;
import com.example.search.repository.SearchRecipeRepository;

@Service
public class SearchRecipeService {
	private SearchRecipeRepository searchRepo;
	private CategoryRepository categoryRepo;

	@Autowired
	public SearchRecipeService(SearchRecipeRepository searchRepo, CategoryRepository categoryRepo) {
		this.searchRepo = searchRepo;
		this.categoryRepo = categoryRepo;
	}

	@RabbitListener(queues = "recipe.order")
	public void getRecipeData(Recipe recipe) {
		System.out.println("----- getRecipeData Log -----");
		System.out.println(recipe);
		Set<String> stuffs = new HashSet<>();
		if (recipe.getStuffRecipe() != null) {
			for (StuffRecipe stuff : recipe.getStuffRecipe()) {
				stuffs.add(stuff.getStuffName());
			}
		}
		List<Category> category = categoryRepo.findByName(recipe.getCategory());

		SearchRecipe addRecipe = SearchRecipe.builder().recipeId(recipe.getRecipeId()).name(recipe.getRecipeName())
				.stuff(stuffs).category(category).build();
		System.out.println(addRecipe);
		searchRepo.save(addRecipe);
	}

	@RabbitListener(queues = "recipe.order.id")
	public void DeleteData(long recipeId) {
		System.out.println("----- DeleteData Log -----");
		System.out.println("지울 data Id: " + recipeId);
		long id = searchRepo.findByRecipeId(recipeId) == null ? 0 : searchRepo.findByRecipeId(recipeId).getId();
		SearchRecipe recipe = searchRepo.findById(id).orElse(null);
		System.out.println(id);
		if (recipe == null) {
			return;
		}
		searchRepo.delete(recipe);
	}
}
