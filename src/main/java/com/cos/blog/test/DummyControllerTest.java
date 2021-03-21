package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController//html 파일이 아니라 data를 리턴해주는 
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	
	
	//delete 예제
	//http://localhost:8000/blog/dummy/user
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제실패 ";
		}
		
		return "삭제완료 id : " + id;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//update 테스트
	//email, password 받아야 한다  (두개를 수정해야해서)
	//http://localhost:8000/blog/dummy/user
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser ) {//@RequestBody : json 데이터 받으려면 필요함 , json 데이터를 요청 => java object (MessageConverter 의 Jackson 라이브러리가 변환해서) 로 변환해서 받아준다   
		System.out.println("id:" + id);
		System.out.println("password:" + requestUser.getPassword());
		System.out.println("email : "+ requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다."); 
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		
		//requestUser.setId(id);
		//requestUser.setUsername("amor");
		//userRepository.save(user);//원래 save는 insert 할때 쓴다 @@@
		//더티 체킹
		return user;
	}
	
	
	
	
	
	//select 여러개 받아오는것
	//http://localhost:8000/blog/dummy/user (특별히 파라미터를 받을 필요가 없다. 전체를 다 받을)
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한페이지당 2건에 데이터를 리턴받아 볼 예제 
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC)Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
//		if(pagingUser.isLast()) {
//			
//		}
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	
	
	
	
	
	
	
	//select test
	//{id} : 주소로 파라미터를 전달 받을수 있다.
	//http://localhost:8000/blog/dummy/user/6
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//user/4번을 찾으면 내가디비에서 못찾아오게 되면  user가 null이될 것 아냐? 그럼 return null이 리턴 되잖아. 그럼 프로그램에 문제가 되지 않겠니?
		//Optional 로 너의 User객체를 감싸서 가져올테니 널인지 아닌지 판단해서 리턴해
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다.id:" + id);
			}
		});
		//요청 : 웹브라우저  
		//user 객체 = 자바 오브젝트  
		//변환 (웹브라우저가 이해할 수 있는 데이터) -> json (gson 라이브러리 ) 
		//스프링부트 = MessageConverter 라는 애가응답시에 자동 작동한다.
		//만약 자바 오브젝트를 리턴하게 되면 MessageConverter 가 Jackson 라이브러리를 호출해서 user 오브젝트를 json 으로 변환해서 브라우저에게 던져준
		return user;
	}
	
	
	
	
	
	
	
	
	//http://localhost:8000/blog/dummy/join (요청)
	//http 의 body에 username, password, email 데이터를 가지고 (요청)
	
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id :" + user.getId());
		System.out.println("username :" + user.getUsername());
		System.out.println("password :" + user.getPassword());
		System.out.println("email :" + user.getEmail());
		System.out.println("role :" + user.getRole());
		System.out.println("createDate :" + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료";
	}
}
