package com.example.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.search.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
