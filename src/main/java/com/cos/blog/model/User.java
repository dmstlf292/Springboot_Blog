package com.cos.blog.model;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
     
   

@Data//getter & setter
@NoArgsConstructor//bean 생성자
@AllArgsConstructor//전체 생성자
@Builder // 빌더 패턴!!
//JPA = ORM -> JAVA(다른언어도 마찬가지)에 있는 object를 테이블로 매핑해주는 
@Entity//user클래스가 자동으로 mysql에 테이블이 생성된다. 
//@DynamicInsert //insert시 null인 필드를 제외시켜준
public class User {
	
	@Id//기본
	@GeneratedValue(strategy=GenerationType.IDENTITY)//프로젝트에서 연결된 디비에 넘버링 전략을 따라간다. (mysql : auto increment 사용하겠다는  )
	private int id;//auto-increment
	
	@Column(nullable = false, length=30, unique=true)
	private String username;
	
	@Column(nullable = false, length=100) // 해쉬로 비밀번호 암호화 할 것이라서 넉넉하게 잡
	private String password;
	
	@Column(nullable = false, length=50)
	private String email;
	
	
	//DB는 roletype 이라는게 없다. 
	@Enumerated(EnumType.STRING)
	//@ColumnDefault("'user'") // " " 안에 '' 이것도 같이 넣어줘야한다.  (문자라는것을 알려준다. )
	private RoleType role; //Enum을 쓰는게 좋다// ADMIN, USER
	
	
	@CreationTimestamp //시간이 자동으로 입력되는 것 
	private Timestamp createDate;//회원가입한 시간 
	
	
}