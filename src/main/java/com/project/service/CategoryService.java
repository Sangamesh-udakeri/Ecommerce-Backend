package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Category;
import com.project.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public Category addCategory(Category category) {

		return categoryRepository.save(category);
	}

	public Category getCategoryByid(Integer id) {

		return categoryRepository.findById(id).get();
	}

	public List<Category> getAllCategories() {

		return categoryRepository.findAll();
	}

	public List<Category> deleteCategoryById(Integer id) {
		categoryRepository.deleteById(id);
		return getAllCategories();
	}

	public Category updateCategoryById(Category category, Integer id) {
		Category category1 = categoryRepository.findById(id).get();
		category1.setCategoryName(category.getCategoryName());
		category1.setDescriprion(category.getDescriprion());
		category1.setImageUrl(category.getImageUrl());
		return categoryRepository.save(category1);
	}
}
