package com.cos.blog3.repository;

//import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


//import com.cos.blog3.dto.ReplySaveRequestDto;
import com.cos.blog3.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	// 인터페이스 퍼블릭 생략 가능
	@Modifying
	@Query(value="INSERT INTO reply( userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())" , nativeQuery = true)
	int mSave(int userId, int boardId, String content); //  업데이트된 행의 개수를 리턴해줌. 
}
