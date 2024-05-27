package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.AuthenticationToken;
import com.project.model.User;

import java.util.List;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {

	AuthenticationToken findByUser(User user);

	AuthenticationToken findByToken(String token);
}
