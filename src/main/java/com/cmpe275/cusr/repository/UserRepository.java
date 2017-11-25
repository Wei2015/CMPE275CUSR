package com.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmpe275.cusr.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}