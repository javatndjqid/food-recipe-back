package com.example.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.search.repository.SearchRecipeRepository;

@Service
public class SearchRecipeService {
	private SearchRecipeRepository searchRepo;
	
	@Autowired
	public SearchRecipeService(SearchRecipeRepository searchRepo) {
		this.searchRepo=searchRepo;
	}
	
	public void getRecipeData() {
		
	}
}
