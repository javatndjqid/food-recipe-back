package com.example.search.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
import com.google.gson.Gson;

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
	public void getRecipeData(Recipe recipe) throws IOException {
		Set<String> stuffs = new HashSet<>();
		if (recipe.getStuffRecipe() != null) {
			for (StuffRecipe stuff : recipe.getStuffRecipe()) {
				stuffs.add(stuff.getStuffName());
			}
		}
		List<Category> category = categoryRepo.findByName(recipe.getCategory());

		SearchRecipe addRecipe = SearchRecipe.builder().recipeId(recipe.getRecipeId()).name(recipe.getRecipeName())
				.stuff(stuffs).category(category).build();
		if (recipe.getImage() == null || recipe.getImage()
				.equals("https://3.bp.blogspot.com/-ZKBbW7TmQD4/U6P_DTbE2MI/AAAAAAAADjg/wdhBRyLv5e8/s1600/noimg.gif")) {
			StringBuilder builder = new StringBuilder();
			builder.append("https://p31ybo8hml.execute-api.ap-northeast-2.amazonaws.com/v1/mypage/recipes/"
//			builder.append("http://192.168.0.29:8080/recipes/"
					+ recipe.getRecipeId());
			URL url = new URL(builder.toString());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			byte[] read = con.getInputStream().readAllBytes();

			String data = new String(read);

			if (data.isBlank())
				return;

			Recipe[] recipeObject = new Gson().fromJson(data, Recipe[].class);
			System.out.println(recipeObject[0].getRecipefile().isEmpty());
			if (recipeObject[0].getRecipefile().isEmpty()) {
				searchRepo.save(addRecipe);
				return;
			}

			addRecipe.setImage(recipeObject[0].getRecipefile().get(0).getDataUrl());

		} else {
			addRecipe.setImage(recipe.getImage());
		}
		searchRepo.save(addRecipe);
	}

	@RabbitListener(queues = "recipe.order.id")
	public void DeleteData(long recipeId) {
		long id = searchRepo.findByRecipeId(recipeId) == null ?
				0 : searchRepo.findByRecipeId(recipeId).getId();
		
		SearchRecipe recipe = searchRepo.findById(id).orElse(null);
		if (recipe == null) {
			return;
		}
		searchRepo.delete(recipe);	
	}
}
