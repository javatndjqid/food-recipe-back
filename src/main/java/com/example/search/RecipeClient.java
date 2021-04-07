package com.example.search;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "recipe", url = "http://192.168.0.29:8080")
//@FeignClient(name = "recipe", url = "http://ec2-13-209-41-162.ap-northeast-2.compute.amazonaws.com")
public interface RecipeClient {
	@GetMapping("/recipes/{recipeId}")
	String getRecipeDetail(@PathVariable("recipeId") long recipeId);
}
