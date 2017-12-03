package com.cmpe275.cusr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cmpe275.cusr.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u from User u WHERE u.userUId = ?1 AND u.email = ?2")
	User findByUIdEmail(String USER_UID, String EMAIL);
}