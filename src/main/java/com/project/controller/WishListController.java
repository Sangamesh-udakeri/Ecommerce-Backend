package com.project.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ProductDto;
import com.project.dto.WishListDto;
import com.project.exception.AuthenticationFailedException;
import com.project.exception.WishListCreationFailed;
import com.project.model.Product;
import com.project.model.User;
import com.project.model.WishList;
import com.project.service.AuthenticationService;
import com.project.service.WishListService;

@RequestMapping("/wishlist")
@RestController
public class WishListController {

	@Autowired
	AuthenticationService authenticationService;
	@Autowired
	WishListService wishListService;
	@PostMapping("/add/{token}")
	public WishListDto addToWishList(@RequestBody Product product,@RequestParam String token) throws AuthenticationFailedException, WishListCreationFailed {
		authenticationService.authenticate(token);
		User user = authenticationService.getUserByToken(token);
		WishList list=new WishList(user, product, new Date());
		return wishListService.createWishList(list);
	}
	@GetMapping("/getAll")
	public List<WishList> getAll(){
		return wishListService.getAll();
	}
	
	@GetMapping("/{token}")
	public List<ProductDto> getWishList(@PathVariable String token) throws AuthenticationFailedException{
		authenticationService.authenticate(token);
		User user = authenticationService.getUserByToken(token);
		return wishListService.getWishListForUser(user);
	}
}
