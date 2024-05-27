package com.project.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ResponseDto;
import com.project.dto.SignInDto;
import com.project.dto.SignInResponseDto;
import com.project.dto.SignUpDto;
import com.project.exception.AuthenticationFailedException;
import com.project.exception.RegisterdUserException;
import com.project.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/signUp")
	public ResponseDto signUp(@RequestBody SignUpDto signUpDto) throws RegisterdUserException {
		
		return userService.signUp(signUpDto);
	}
	@PostMapping("/signIn")
	public SignInResponseDto signIn(@RequestBody SignInDto signInDto) throws NoSuchAlgorithmException, AuthenticationFailedException {
		
		return userService.signIn(signInDto);
	}
}
