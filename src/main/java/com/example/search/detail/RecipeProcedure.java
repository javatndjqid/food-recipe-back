package com.example.search.detail;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeProcedure {

	private long id;
	private String recipeProcedureImage;
	private String recipeProcedure;

	private List<RecipeProcedureFile> RecipeProcedurefile;

}
