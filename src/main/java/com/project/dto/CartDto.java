package com.project.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartDto {

	private List<CartItemDto> catrItemDtos;
	private double totalPrice;

}
