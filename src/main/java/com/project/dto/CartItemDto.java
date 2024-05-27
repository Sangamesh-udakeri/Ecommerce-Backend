package com.project.dto;

import com.project.model.Cart;
import com.project.model.Product;

import lombok.Data;

@Data
public class CartItemDto {

	private Integer id;
	private Integer quantity;
	private Product product;

	public CartItemDto(Cart cart) {
		this.id = cart.getId();
		this.quantity = cart.getQuantity();
		this.product = cart.getProduct();
	}
}
