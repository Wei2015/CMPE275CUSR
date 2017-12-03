package com.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cmpe275.cusr.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT user FROM USER WHERE user.USER_UID = ?1 AND user.EMAIL = ?1")
	User findByUIdEmail(String USER_UID, String EMAIL);
}