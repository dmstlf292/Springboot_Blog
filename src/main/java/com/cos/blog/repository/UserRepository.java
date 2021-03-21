package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;


//DAO랑 같은 역할을 한
//자동으로 bean 등록이 된다.
//@Repository 가 생략이 가능하다 
public interface UserRepository extends JpaRepository<User, Integer>{

	
	
	
	
}
