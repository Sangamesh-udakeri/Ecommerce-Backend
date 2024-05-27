package com.project.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exception.AuthenticationFailedException;
import com.project.model.AuthenticationToken;
import com.project.model.User;
import com.project.repository.TokenRepository;

@Service
public class AuthenticationService {

	@Autowired
	TokenRepository tokenRepository;

	public void authenticate(String token) throws AuthenticationFailedException {

		if (Objects.isNull(token)) {

			throw new AuthenticationFailedException("invalid token");
		}
		if(Objects.isNull(getUserByToken(token))) {
			throw new AuthenticationFailedException("invalid token");
		}
	}

	public User getUserByToken(String token) {
		AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
		if (Objects.isNull(authenticationToken)) {
			return null;
		}
		return authenticationToken.getUser();
	}

}
