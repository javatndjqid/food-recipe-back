package com.example.search.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

	private long id;

	private String name;
	private long price;
	private String weight;
	private String origin;
	private String shelfLife;
	private String productTitleImage;
	private String productDetailImage;
	private String stuff;
	private String category;

}
