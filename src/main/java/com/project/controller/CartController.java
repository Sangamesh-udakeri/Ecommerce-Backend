package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.AddCartDto;
import com.project.dto.CartDto;
import com.project.dto.ResponseDto;
import com.project.exception.AuthenticationFailedException;
import com.project.model.Cart;
import com.project.model.Product;
import com.project.model.User;
import com.project.service.AuthenticationService;
import com.project.service.CartService;
import com.project.service.ProductService;

@RequestMapping("/cart")
@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/add/{token}")
	public ResponseDto addToCart(@RequestBody AddCartDto cartDto, @RequestParam String token)
			throws AuthenticationFailedException {
		authenticationService.authenticate(token);
		User user = authenticationService.getUserByToken(token);

		cartService.addToCart(cartDto, user);
		return new ResponseDto("true", "added to cart");
	}

	@GetMapping("/getAll")
	public CartDto getCartItems(@RequestParam String token) throws AuthenticationFailedException {
		authenticationService.authenticate(token);
		User user = authenticationService.getUserByToken(token);
		return cartService.listCartDto(user);

	}

}
