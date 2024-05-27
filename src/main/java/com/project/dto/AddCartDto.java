package com.project.dto;

import lombok.Data;

@Data
public class AddCartDto {

	private Integer id;
	private Integer productId;
	private Integer quantity;
}
