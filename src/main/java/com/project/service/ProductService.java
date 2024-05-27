package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.ProductDto;
import com.project.model.Category;
import com.project.model.Product;
import com.project.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public Product createProduct(ProductDto productDto, Category category) {

		Product product = new Product();
		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setCategory(category);
		return productRepository.save(product);
	}

	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductDto> dtos = new ArrayList<>();

		for (Product product : products) {
			ProductDto dto = new ProductDto();
			dto.setDescription(product.getDescription());
			dto.setCategoryId(product.getCategory().getId());
			dto.setImageUrl(product.getImageUrl());
			dto.setName(product.getName());
			dto.setPrice(product.getPrice());

			dtos.add(dto);
		}
		return dtos;
	}

	public List<ProductDto> getAllProductsByCategory(Integer id) {
		List<Product> products = productRepository.findByCategoryId(id);
		List<ProductDto> dtos = new ArrayList<>();
		for (Product product : products) {
			ProductDto dto = new ProductDto();
			dto.setDescription(product.getDescription());
			dto.setCategoryId(product.getCategory().getId());
			dto.setImageUrl(product.getImageUrl());
			dto.setName(product.getName());
			dto.setPrice(product.getPrice());

			dtos.add(dto);
		}
		return dtos;
	}

	public Product updateProduct(ProductDto productDto) {

		Product product = new Product();
		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		return productRepository.save(product);
	}

	public List<ProductDto> deleteProduct(Integer id) {
		productRepository.deleteById(id);
		List<Product> products = productRepository.findAll();
		List<ProductDto> dtos = new ArrayList<>();

		for (Product product : products) {
			ProductDto dto = new ProductDto();
			dto.setDescription(product.getDescription());
			dto.setCategoryId(product.getCategory().getId());
			dto.setImageUrl(product.getImageUrl());
			dto.setName(product.getName());
			dto.setPrice(product.getPrice());

			dtos.add(dto);
		}
		return dtos;
	}

	public Product findById(Integer productId) {

		return productRepository.findById(productId).get();
	}

}
