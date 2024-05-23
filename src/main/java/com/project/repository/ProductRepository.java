package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public List<Product> findByCategoryId(Integer id);
}
