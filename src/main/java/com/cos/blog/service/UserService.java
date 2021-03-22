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
	
	
	@Transactional
	public void 회원수정 (User user) {
		//수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정하기
		//select 해서 User 오브젝트를 디비로부터 가져오는 이유는 영속화를 하기 위해서
		//영속화된 오브젝트를 변경하면 자동으로 디비에 update문을 날려준다. 
		User persistance = userReposiroty.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패 ");
		});
		String rawPassword = user.getPassword();
		String encPassword=encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		//회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = 커밋이 자동으로 된다. 
		//(영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update 문을 날려준다 )
	}
	
}
