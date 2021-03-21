package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청했을때 응답을 http 파일을 응답해주면 -> @Controller

//사용자가 요청하면 data를 응답해준다
@RestController
public class HttpControllerTest {

	// http://localhost:8000/blog/http/get 
	//인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다. 
	// http://localhost:8080/http/get (selete)
	@GetMapping("/http/get")
	public String getTest(Member m) {//MessageConverter (springboot)
		return "get 요청 " +m.getId() +"," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post") // text/plain, application/json
	public String postTest(@RequestBody Member m) {//MessageConverter (springboot)
		return "post 요청 " +m.getId() +"," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 " +m.getId() +"," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청 ";
	}
}
