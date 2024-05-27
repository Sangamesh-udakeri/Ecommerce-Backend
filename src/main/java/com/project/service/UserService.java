package com.project.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.ResponseDto;
import com.project.dto.SignInDto;
import com.project.dto.SignInResponseDto;
import com.project.dto.SignUpDto;
import com.project.exception.AuthenticationFailedException;
import com.project.exception.RegisterdUserException;
import com.project.model.AuthenticationToken;
import com.project.model.User;
import com.project.repository.TokenRepository;
import com.project.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	TokenRepository tokenRepository;

	@Transactional
	public ResponseDto signUp(SignUpDto dto) throws RegisterdUserException {

		List<User> findByEmail = userRepository.findByEmail(dto.getEmail());
		if (!findByEmail.isEmpty()) {
			throw new RegisterdUserException("already registered with this email");
		}
		String hashPassword = "";
		try {
			hashPassword = hashPassword(dto.getPassword());
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setPassword(hashPassword);
		userRepository.save(user);
		final AuthenticationToken authenticationToken = new AuthenticationToken(user);
		tokenRepository.save(authenticationToken);
		ResponseDto responseDto = new ResponseDto("success", "user created");
		return responseDto;
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(password.getBytes());
		byte[] digestm = digest.digest();
		String hash = DatatypeConverter.printHexBinary(digestm).toUpperCase();
		return hash;
	}

	public SignInResponseDto signIn(SignInDto signInDto)
			throws AuthenticationFailedException, NoSuchAlgorithmException {
		List<User> findByEmail = userRepository.findByEmail(signInDto.getEmail());
		User user = findByEmail.get(0);
		if (user == null) {

			throw new AuthenticationFailedException("User Not Found Please Sign Up Try Again");
		}
		if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
			throw new AuthenticationFailedException("wrong email and password");
		}

		AuthenticationToken authenticationToken = tokenRepository.findByUser(user);
		if(authenticationToken==null) {
			throw new AuthenticationFailedException("wrong token");
		}
		return new SignInResponseDto("success", authenticationToken.getToken());
	}
}
