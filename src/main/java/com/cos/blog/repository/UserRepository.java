package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;


//DAO랑 같은 역할을 한
//자동으로 bean 등록이 된다.
//@Repository 가 생략이 가능하다 
public interface UserRepository extends JpaRepository<User, Integer>{
	
	//select * from user where username=1?;
	Optional<User> findByUsername(String username);
	
	
}
	//로그인을 위한 함수 만들기
	//JPA Naming 전략, 쿼리 
	// select * from user where username=?1 and password=?2; 이런 쿼리가 동작함 
	//User findByUsernameAndPassword(String username, String password);
	
	
	//@Query(value="select * from user where username=?1 and password=?2", nativeQuery = true)
	//User login(String username, String password);
	