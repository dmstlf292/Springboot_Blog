package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data//getter & setter
@NoArgsConstructor//bean 생성자
@AllArgsConstructor//전체 생성자
@Builder // 빌더 패턴!!
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;//PK
	
	@Column(nullable = false, length=100)
	private String title;
	
	@Lob//대용량데이터일때 사용한다. 
	private String content; // summernote 라이브러 사용할것이다. html 태그가 섞여서 디자인이 된다. 글자용량이 엄청 커진다.
	
	
	
	private int count;//조회
	
	
	
	
	@ManyToOne(fetch=FetchType.EAGER)//연관관계 맺어주는 것 : Many = Board, One = user 즉, 한명의 유저는 여러개의 게시글을 쓸 수 있다.
	@JoinColumn(name="userId")
	private User user;//디비는 오브젝트를 저장할 수 없다. 외래키FK), 자바는 오브젝트를 저장할 수 있다. 
	
	
	
	
	
	//cascade=CascadeType.REMOVE -> board게시글 삭제하면 거기 댓글들도 같이 삭제하겠다는 뜻  
	@OneToMany(mappedBy="board", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)//한개의 게시글에 여러개의 답변 가지고 있음 , mappedBy가 적혀있으면 연관관계의 주인이 아니다  (난, FK가 아니예요 ) => 디비에 컬럼을 만들지 마세요..라는 뜻 
	//그래서 FK 는 Board에 있는게  아니라 Reply테이블의 Board가 FK라는 뜻 
	
	//무한참조 방지하기 위해서  @@JsonIgnoreProperties 사용 
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;//중요 
	
	
	
	
	@CreationTimestamp
	private Timestamp createDate;
	
	
}
