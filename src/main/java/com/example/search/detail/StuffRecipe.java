package com.example.search.detail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StuffRecipe {

	private int id;
	private String quantity;
	private String stuffName;

}
