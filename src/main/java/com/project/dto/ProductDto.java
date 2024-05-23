package com.project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {
	
	private @NotNull String name;
	private @NotNull String imageUrl;
	private @NotNull String price;
	private @NotNull String description;
	private  @NotNull Integer categoryId;
}
