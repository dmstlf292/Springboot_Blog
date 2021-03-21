package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 빈에 등록을 해준다 = 즉, IoC를 해준다는 뜻 
@Service
public class UserService {
	
	@Autowired
	private UserRepository userReposiroty;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	//회원가입 서비스 함수 
	@Transactional
	public void 회원가입 (User user) {
		String rawPassword = user.getPassword();//1234 원
		String encPassword =encoder.encode(rawPassword);//해쉬화 해준
		
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userReposiroty.save(user);
	}

}
