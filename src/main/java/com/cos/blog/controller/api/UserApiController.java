package com.cos.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;

@RestController
public class UserApiController {
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {//요청 받는것 
		System.out.println("UserApiController : save 호출");
		//실제로 디비에 insert하고 아래에서 return 하면 된다. 
		return new ResponseDto<Integer>(HttpStatus.OK, 1);//자바오브젝트를 json 으로 변환해서 리턴해준다 (jackson 라이브러리가 실행해줌) 
				
	} 
}
