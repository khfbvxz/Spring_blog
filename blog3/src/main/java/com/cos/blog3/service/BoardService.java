package com.cos.blog3.service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog3.dto.ReplySaveRequestDto;
import com.cos.blog3.model.Board;
// import com.cos.blog3.model.Reply;
import com.cos.blog3.model.User;
import com.cos.blog3.repository.BoardRepository;
import com.cos.blog3.repository.ReplyRepository;
// import com.cos.blog3.repository.UserRepository;

import lombok.RequiredArgsConstructor;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌  IoC를 해준다.
@Service
@RequiredArgsConstructor  // <= 없애면 아래 주석 풀어 
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;
	
//	 public BoardService(BoardRepository bRepo, ReplyRepository rRepo) {
//		 this.boardRepository = bRepo;
//		 this.replyRepository = rRepo;
//	 }
	@Transactional
	public void 글쓰기(Board board, User user) {  // title , content 
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	@Transactional(readOnly=true)
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	@Transactional(readOnly=true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
					.orElseThrow(()-> {
						return new IllegalArgumentException("글 상세보기 : 아이디를 찾을 수 없습니다.");
					});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id , Board requestBoard) {
		// 글을 수정할려면 영속화를 먼저 만들어야 한다.
		Board board = boardRepository.findById(id)
				.orElseThrow(()-> {
					return new IllegalArgumentException("글 찾기 실패  : 아이디를 찾을 수 없습니다.");
				}); // 영속화 완료 
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시( Service가 종료될 때) 트랜잭션이 종료된다. 이떄 더티체킹 -  자동 업데이트가 됨 . db flush 
	}
	
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		
//		User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()-> {
//			return new IllegalArgumentException("댓글쓰기실패 : 유저 id를 찾을 수 없습니다. ");
//		});
//		
//		Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()-> {
//			return new IllegalArgumentException("댓글쓰기실패 : 게시글 id를 찾을 수 없습니다. ");
//		});
//		Reply reply = Reply.builder()
//				.user(user)
//				.board(board)
//				.content(replySaveRequestDto.getContent())
//				.build();
//		
//		Reply reply = new Reply();
//		reply.update(user, board, replySaveRequestDto.getContent());
		
	
		replyRepository.mSave(replySaveRequestDto.getUserId(),replySaveRequestDto.getBoardId(),replySaveRequestDto.getContent());
	}
	
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}
	
}