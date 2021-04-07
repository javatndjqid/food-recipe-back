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
		System.out.println(data);
		LectureResponse[] lecRes = new Gson().fromJson(data, LectureResponse[].class);
		System.out.println("===== lecture =====");
		System.out.println(lecRes);
		return lecRes;
	}

	public ProductResponse[] getProduct() {
		String data = market.getProduct();
		System.out.println(data);
		ProductResponse[] product = new Gson().fromJson(data, ProductResponse[].class);
		System.out.println("===== product =====");
		System.out.println(product);
		return product;
	}

	public Recipe getRecipeDetail(long recipeId) {
		String json = recipe.getRecipeDetail(recipeId);
		System.out.println(json);
		Recipe[] detail = new Gson().fromJson(json, Recipe[].class);
		System.out.println(detail);
		return detail[0];
	}
}
