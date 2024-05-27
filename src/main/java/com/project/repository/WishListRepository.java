package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.User;
import com.project.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Integer> {

	List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
