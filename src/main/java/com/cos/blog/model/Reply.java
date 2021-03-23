package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.dto.ReplySaveRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;//PK
	
	@Column(nullable = false, length=200)
	private String content;
	
	@ManyToOne//하나의 게시글에 여러개 답변쓰기     
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne//여러개 답변을 하나의 유저가 쓸 수 있다.  
	@JoinColumn(name="userId")
	private User user;
	

	@CreationTimestamp
	private Timestamp createDate;
	
	
}
