package com.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.AddCartDto;
import com.project.dto.CartDto;
import com.project.dto.CartItemDto;
import com.project.model.Cart;
import com.project.model.Product;
import com.project.model.User;
import com.project.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductService productService;

	public void addToCart(AddCartDto cartDto, User user) {

		Product product = productService.findById(cartDto.getProductId());
		Cart cart = new Cart();
		cart.setCretedDate(new Date());
		cart.setProduct(product);
		cart.setQuantity(cartDto.getQuantity());
		cart.setUser(user);
		cartRepository.save(cart);
	}

	public CartDto listCartDto(User user) {

		List<Cart> cartList = cartRepository.findAllByUserOrderByCretedDateDesc(user);
		List<CartItemDto> cartItemDtos = new ArrayList<>();
		double totalPrice = 0;
		for (Cart cart : cartList) {
			CartItemDto cartItemDto = new CartItemDto(cart);
			totalPrice += cartItemDto.getQuantity() * Integer.parseInt(cart.getProduct().getPrice());
			cartItemDtos.add(cartItemDto);
		}
		CartDto cartDto=new CartDto();
		cartDto.setTotalPrice(totalPrice);
		cartDto.setCatrItemDtos(cartItemDtos);
		return cartDto;
	}

}
