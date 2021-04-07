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
		System.out.println("----- getRecipeData Log -----");
		System.out.println(recipe);
		Set<String> stuffs = new HashSet<>();
		System.out.println(recipe.getStuffRecipe());
		System.out.println("===== getImage() =====");
		System.out.println(recipe.getRecipefile());

		if (recipe.getStuffRecipe() != null) {
			for (StuffRecipe stuff : recipe.getStuffRecipe()) {
				System.out.println(stuff.getStuffName());
				stuffs.add(stuff.getStuffName());
			}
		}
		List<Category> category = categoryRepo.findByName(recipe.getCategory());

		SearchRecipe addRecipe = SearchRecipe.builder().recipeId(recipe.getRecipeId()).name(recipe.getRecipeName())
				.stuff(stuffs).category(category).build();
		if (recipe.getImage() == null || recipe.getImage()
				.equals("https://3.bp.blogspot.com/-ZKBbW7TmQD4/U6P_DTbE2MI/AAAAAAAADjg/wdhBRyLv5e8/s1600/noimg.gif")) {
			System.out.println("image 삽입 실행");
			StringBuilder builder = new StringBuilder();
			builder.append("http://ec2-13-209-41-162.ap-northeast-2.compute.amazonaws.com:8080/recipes/"
//			builder.append("http://192.168.0.29:8080/recipes/" 
					+ recipe.getRecipeId());
			System.out.println("builder");
			URL url = new URL(builder.toString());
			System.out.println("url 생성");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			System.out.println("con 생성");

			byte[] read = con.getInputStream().readAllBytes();

			String data = new String(read);

			if (data.isBlank())
				return;

			System.out.println("===== Data =====");
			System.out.println(data);

			Recipe[] recipeObject = new Gson().fromJson(data, Recipe[].class);
			System.out.println(recipeObject[0].getRecipefile().isEmpty());
			if (recipeObject[0].getRecipefile().isEmpty()) {
				searchRepo.save(addRecipe);
				return;
			}

			System.out.println("===== Recipe Response =====");
			System.out.println(recipeObject[0].getRecipefile().get(0).getDataUrl());

			addRecipe.setImage(recipeObject[0].getRecipefile().get(0).getDataUrl());

		} else {
			addRecipe.setImage(recipe.getImage());
		}
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
