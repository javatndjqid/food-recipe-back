package com.example.search.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.search.entity.Category;
import com.example.search.entity.SearchRecipe;
import com.example.search.repository.CategoryRepository;
import com.example.search.repository.SearchRecipeRepository;

@RestController
public class SearchController {
	private SearchRecipeRepository searchRepo;
	private CategoryRepository categoryRepo;

	@Autowired
	public SearchController(SearchRecipeRepository searchRepo, CategoryRepository categoryRepo) {
		this.searchRepo = searchRepo;
		this.categoryRepo = categoryRepo;
	}

	@GetMapping(value = "/recipe/search")
	public List<SearchRecipe> getRecipes() {
		return searchRepo.findAll();
	}

	@PostMapping(value = "/recipe/search")
	public SearchRecipe setSearchRecipeData(@RequestBody SearchRecipe recipe) {
		searchRepo.save(recipe);
		return recipe;
	}

	@PostMapping(value = "/recipe/search/multi-post")
	public List<SearchRecipe> setMultiSearchRecipe(@RequestBody List<SearchRecipe> recipes) {
		return searchRepo.saveAll(recipes);
	}

	@DeleteMapping(value = "recipe/search/{id}")
	public SearchRecipe delSearchRecipe(@PathVariable long id, HttpServletResponse res) {
		SearchRecipe recipe = searchRepo.findById(id).orElse(null);
		if (recipe == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		searchRepo.delete(recipe);
		return recipe;
	}

	@GetMapping(value = "/recipe/search/categories")
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}

	@GetMapping(value = "/recipe/search/category/{id}")
	public List<SearchRecipe> getCategoryRecipe(@PathVariable long id) {
		return id == 0 ? searchRepo.findAll() : searchRepo.findByCategoryId(id);
	}

	@GetMapping(value = "/recipe/search/all-stuffs")
	public Set<String> getStuffRecipe() {
		Set<String> stuffs = new HashSet<String>();
		List<SearchRecipe> recipes = searchRepo.findAll();
		for (SearchRecipe recipe : recipes) {
			Iterator<String> iter = recipe.getStuff().iterator();
			while (iter.hasNext()) {
				String stuff = iter.next();
				stuffs.add(stuff);
			}
		}
		return stuffs;
	}

}
