package com.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.ProductDto;
import com.project.dto.WishListDto;
import com.project.exception.WishListCreationFailed;
import com.project.model.User;
import com.project.model.WishList;
import com.project.repository.WishListRepository;

@Service
public class WishListService {

	@Autowired
	WishListRepository wishListRepository;

	public WishListDto createWishList(WishList list) throws WishListCreationFailed {

		WishList save = wishListRepository.save(list);
		if (save == null) {
			throw new WishListCreationFailed("failed to add wishlist");
		}
		WishListDto dto = new WishListDto("success", "added to wishlist", new Date());
		return dto;
	}

	public List<WishList> getAll() {
		return wishListRepository.findAll();
	}

	public List<ProductDto> getWishListForUser(User user) {

		List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<ProductDto> dtos = new ArrayList<>();
		for (WishList wishList : wishLists) {
			ProductDto dto = new ProductDto();
			
			dto.setDescription(wishList.getProduct().getDescription());
			dto.setImageUrl(wishList.getProduct().getImageUrl());
			dto.setName(wishList.getProduct().getName());
			dto.setPrice(wishList.getProduct().getPrice());
			dtos.add(dto);
		}
		return dtos;
	}

}
