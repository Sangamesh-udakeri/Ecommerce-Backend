package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Category;
import com.project.service.CategoryService;

@RequestMapping("/category")
@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping("/add")
	public Category addCategory(@RequestBody Category category) {

		return categoryService.addCategory(category);
	}

	@GetMapping("/getbyid/{id}")
	public Category getCategoryByid(@PathVariable Integer id) {

		return categoryService.getCategoryByid(id);
	}

	@GetMapping("/getall")
	public List<Category> getAllCategories() {

		return categoryService.getAllCategories();
	}

	@DeleteMapping("/delete/{id}")
	public List<Category> deleteCategoryById(@PathVariable Integer id) {
		categoryService.deleteCategoryById(id);
		return getAllCategories();
	}
	@PutMapping("/update/{id}")
	public Category updateCategoryById(@RequestBody  Category category,@PathVariable Integer id) {
		return categoryService.updateCategoryById(category, id);
	}
}
