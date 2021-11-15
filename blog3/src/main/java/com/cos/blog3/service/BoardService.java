package com.cos.blog3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog3.model.Board;
import com.cos.blog3.model.User;
import com.cos.blog3.repository.BoardRepository;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌  IoC를 해준다.
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) {  // title , content 
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
}