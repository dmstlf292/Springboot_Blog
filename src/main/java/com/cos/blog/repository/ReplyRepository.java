package com.cos.blog.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

	@Modifying
	@Query(value="insert into reply(userId, boardId, content,createDate) values(?1,?2,?3,now())", nativeQuery= true)
	int mSave(int userId, int boardId, String content);//insert, update, delete 를 수행하면 리턴값을 업데이트 된 행의 개수를 리턴해준다. 
	//1 리턴-> 1개가 업데이트, 0 리턴 -> 저장안됨, -1 리턴->오
	
}
