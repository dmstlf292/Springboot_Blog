package com.cos.blog.service; // 호출하는 서비스 페이지  



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;


//스프링이 컴포넌트 스캔을 통해서 빈에 등록을 해준다 = 즉, IoC를 해준다는 뜻 
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardReposiroty;
	
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	@Transactional
	public void 글쓰기(Board board, User user) {//title, content, count
		board.setCount(0);
		board.setUser(user);
		boardReposiroty.save(board);
	}
	
	@Transactional(readOnly=true)
	public Page<Board> 글목록(Pageable pageable){
		return boardReposiroty.findAll(pageable);
	}
	
	
	@Transactional(readOnly=true)
	public Board 글상세보기(int id) {
		return boardReposiroty.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("Fail!");
				});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		 boardReposiroty.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id, Board requestBoard) {
		 Board board = boardReposiroty.findById(id)
				 .orElseThrow(()->{
						return new IllegalArgumentException("Fail!");
					});//영속화 완료 
		 board.setTitle(requestBoard.getTitle());
		 board.setContent(requestBoard.getContent());
		 //해당 함수로 종료시 (service가 종료될때) 트랜잭션이 종료된다. 이때 더티체킹이 일어나면서 자동 업뎃을 실행한다 (db 쪽으로 flush 된다)
	}
	
	
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySveRequestDto) {
		
//		User user = userRepository.findById(replySveRequestDto.getUserId()).orElseThrow(()->{
//			return new IllegalArgumentException("Fail!");
//		});//영속화 완료 
//		
//		Board board = boardReposiroty.findById(replySveRequestDto.getBoardId()).orElseThrow(()->{
//						return new IllegalArgumentException("Fail!");
//					});//영속화 완료 
		
//		Reply reply = Reply.builder()
//				.user(user)
//				.board(board)
//				.content(replySveRequestDto.getContent())
//				.build();
		
		
//		replyRepository.save(reply);
		
		int result = replyRepository.mSave(replySveRequestDto.getUserId(), replySveRequestDto.getBoardId(), replySveRequestDto.getContent());
		//System.out.println(reply);//오브젝트를 출력하게 되면 자동으로 toString () 이 호출된다 
		System.out.println("Board");
	}
}
