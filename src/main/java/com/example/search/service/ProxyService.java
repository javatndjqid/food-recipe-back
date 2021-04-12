package com.example.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.search.LectureClient;
import com.example.search.MarketClient;
import com.example.search.RecipeClient;
import com.example.search.detail.Recipe;
import com.example.search.response.LectureResponse;
import com.example.search.response.ProductResponse;
import com.google.gson.Gson;

@Service
public class ProxyService {
	@Autowired
	LectureClient lecture;
	@Autowired
	MarketClient market;
	@Autowired
	RecipeClient recipe;

	public LectureResponse[] getLecture() {
		String data = lecture.getLecture();
		LectureResponse[] lecRes = new Gson().fromJson(data, LectureResponse[].class);
		return lecRes;
	}

	public ProductResponse[] getProduct() {
		String data = market.getProduct();
		ProductResponse[] product = new Gson().fromJson(data, ProductResponse[].class);
		return product;
	}

	public Recipe getRecipeDetail(long recipeId) {
		String json = recipe.getRecipeDetail(recipeId);
		Recipe[] detail = new Gson().fromJson(json, Recipe[].class);
		return detail[0];
	}
}
