package com.example.search.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.search.detail.StuffRecipe;
import com.example.search.response.LectureResponse;
import com.example.search.response.ProductResponse;
import com.example.search.service.ProxyService;

@RestController
public class DetailRecipeController {

	private final ProxyService service;

	public DetailRecipeController(ProxyService service) {
		this.service = service;
	}

//	@GetMapping(value = "/recipe/search/detail/{id}")
//	public Recipe getDetailRecipe(@PathVariable long id) {
//		Recipe recipeRes = service.getRecipeDetail(id);
//		System.out.println(recipeRes);
////		Recipe recipe = new Recipe(recipeRes.getRecipe().get(0));
//		return recipeRes;
//	}

	@GetMapping(value = "/recipe/detail/lecture/{category}")
	public List<LectureResponse> getLecture(@PathVariable String category) {
		LectureResponse[] lectureRes = service.getLecture();
		System.out.println("===== category =====");
		System.out.println(category);
		List<LectureResponse> list = new ArrayList<>();
		for (LectureResponse lecture : lectureRes) {
			System.out.println("====== lecture.getCategory ======");
			System.out.println(lecture.getCategory());
			if (list.size() == 4)
				break;
			if (lecture.getCategory().equals(category))
				list.add(lecture);
		}
		return list;
	}

	@PostMapping(value = "/recipe/detail/market")
	public List<ProductResponse> getMarket(List<StuffRecipe> stuff) {
		ProductResponse[] product = service.getProduct();
		System.out.println(product);
		Set<String> stuffs = new HashSet<>();
		for (int i = 0; i < stuff.size(); i++) {
			stuffs.add(stuff.get(i).getStuffName());
		}
		List<ProductResponse> list = new ArrayList<>();
		for (ProductResponse pro : product) {
			if (list.size() == 4)
				break;
			if (!stuffs.contains(pro.getStuff().toString()))
				list.add(pro);

			System.out.println(list);
		}
		return list;
	}
}
