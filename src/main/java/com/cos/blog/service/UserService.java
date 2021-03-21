package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 빈에 등록을 해준다 = 즉, IoC를 해준다는 뜻 
@Service
public class UserService {
	
	@Autowired
	private UserRepository userReposiroty;
	
	public User 회원가입(User user) {
		try {
			userReposiroty.save(user);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			ss
		}
		
	}
	
	
}
