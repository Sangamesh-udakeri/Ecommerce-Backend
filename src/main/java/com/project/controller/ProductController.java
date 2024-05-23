package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ProductDto;
import com.project.model.Category;
import com.project.model.Product;
import com.project.service.CategoryService;
import com.project.service.ProductService;

@RequestMapping("/product")
@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/add")
	public Product createProduct(@RequestBody ProductDto productDto) {
		Category categoryByid = categoryService.getCategoryByid(productDto.getCategoryId());
		return productService.createProduct(productDto, categoryByid);
	}
	@GetMapping("/products")
	public List<ProductDto> getProducts(){
		return productService.getAllProducts();
	}
	@GetMapping("/getAllByCategory/{id}")
	public List<ProductDto> getAllProductByCategory(@PathVariable Integer id){
		
		return productService.getAllProductsByCategory(id);
	}
}
