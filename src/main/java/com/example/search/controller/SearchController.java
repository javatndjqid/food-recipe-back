package com.example.search.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

	@DeleteMapping(value = "recipe/search/{id}")
	public SearchRecipe delSearchRecipe(@PathVariable long id, HttpServletResponse res) {
		SearchRecipe recipe = searchRepo.findById(id).orElse(null);
		if (recipe == null) {
			System.out.println("해당 id를 가진 recipe가 존재하지 않음");
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		searchRepo.delete(recipe);
		return recipe;
	}

	@GetMapping(value = "/recipe/search/{id}")
	public SearchRecipe getSearchDetail(@PathVariable long id, HttpServletResponse res) {
		SearchRecipe recipe = searchRepo.findById(id).orElse(null);
		if (recipe == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return recipe;
	}

	@GetMapping(value = "/recipe/search/categories")
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}

	@GetMapping(value = "/recipe/search/category/{id}")
	public List<SearchRecipe> getCategoryRecipe(@PathVariable long id) {
		List<SearchRecipe> search = id == 0 ? searchRepo.findAll() : searchRepo.findByCategoryId(id);
		return search;
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

	@GetMapping(value = "/recipe/search/stuffs/{categoryId}")
	public List<SearchRecipe> getStuffRecipe(@PathVariable long categoryId, @RequestParam String checkStuff) {
		System.out.println("getStuffRecipe()실행");
		List<SearchRecipe> searchList = categoryId == 0 ? searchRepo.findAll()
				: searchRepo.findByCategoryId(categoryId);
		System.out.println(searchList);
		searchList = searchList.stream().filter(search -> search.getStuff().contains(checkStuff))
				.collect(Collectors.toList());
		System.out.println(searchList);

//		for (SearchRecipe search : searchs) {
//			Set<String> stuffs = search.getStuff();
//			if (!stuffs.contains(checkStuff))
//				continue;
//			searchList.add(search);
//		}
		return searchList;
	}
}
