package com.example.search.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.search.entity.SearchRecipe;

@Repository
public interface SearchRecipeRepository extends JpaRepository<SearchRecipe, Long> {
	List<SearchRecipe> findByCategoryId(long categoryId);

	List<SearchRecipe> findByStuff(String stuff);

	SearchRecipe findByRecipeId(long recipeId);
}
