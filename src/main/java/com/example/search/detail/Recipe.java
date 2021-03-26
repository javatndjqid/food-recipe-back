package com.example.search.detail;

import java.util.List;
import java.util.Set;

import com.example.search.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
	private long id;
	private Category category;
	private Set<String> stuffs;
	private List<String> procedure;
	private String name;
	private String memo;
	private String image;
}
