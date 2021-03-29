package com.example.search.detail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeFile {

	private long id;

	private String fileName;
	private String contentType;
	private long recipeId;

	private String dataUrl;

}
