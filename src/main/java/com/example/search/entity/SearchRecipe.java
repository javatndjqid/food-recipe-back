package com.example.search.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SearchRecipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToMany(targetEntity = Category.class)
	private List<Category> category;
	@ElementCollection
	@CollectionTable(name = "Stuff", joinColumns = @JoinColumn(name = "RECIPE_ID"))
	@Column(name = "STUFF_NAME")
	private Set<String> stuff = new HashSet<>();
	private String name;
	private String image;
}
