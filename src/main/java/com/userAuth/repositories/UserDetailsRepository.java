package com.userAuth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userAuth.models.Users;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users, Long>{
	
	
}
