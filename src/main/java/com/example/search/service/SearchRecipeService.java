package com.example.search.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.search.detail.Recipe;
import com.example.search.entity.SearchRecipe;
import com.example.search.repository.SearchRecipeRepository;

@Service
public class SearchRecipeService {
	private SearchRecipeRepository searchRepo;

	@Autowired
	public SearchRecipeService(SearchRecipeRepository searchRepo) {
		this.searchRepo = searchRepo;
	}

	@RabbitListener(queues = "recipe.order")
	public void getRecipeData(Recipe recipe) {
		System.out.println("----- getRecipeData Log -----");
		System.out.println(recipe);

		SearchRecipe addRecipe = SearchRecipe.builder().recipeId(recipe.getRecipeId()).name(recipe.getRecipeName())
				.image(recipe.getRecipefile().get(0).getDataUrl()).build();
		System.out.println(addRecipe);
		searchRepo.save(addRecipe);
	}

	@RabbitListener(queues = "recipe.order.id")
	public void DeleteData(long id) {
		System.out.println("----- DeleteData Log -----");
		System.out.println("지울 data Id: " + id);
//		SearchRecipe recipe = searchRepo.findByRecipeId(1);
//		System.out.println(recipe);
//		if (recipe == null) {
//			return;
//		}
//		searchRepo.delete(recipe);
	}
}
