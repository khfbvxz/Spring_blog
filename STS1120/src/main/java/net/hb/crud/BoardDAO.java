package net.hb.crud;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
    
  public void dbInsert(BoardDTO dto) {
	System.out.println("\nBoardDAO문서 dbInsert(BoardDTO)");
	System.out.println("dao넘어온 이름 = " + dto.getName());
	System.out.println("dao넘어온 제목 = " + dto.getTitle());
	System.out.println("dao넘어온 내용 = " + dto.getContent());
	System.out.println("dao넘어온 취미 = " + dto.getHobby());
	//System.out.println("dao넘어온 파일 = ");
	//insert into values쿼리문장이 없음
  }//end
  
  public List<BoardDTO> dbSelect() {
	  List<BoardDTO> list = null ;
	  return list ;
  }//end
  
  public BoardDTO dbDetail(int code) {
	  BoardDTO dto = new BoardDTO();
	  return dto;
  }//end
  
  public void dbInsert2(BoardDTO dto) { //복사본
	System.out.println("\nBoardDAO문서 dbInsert2(BoardDTO)");
	System.out.println("dao넘어온 이름 = " + dto.getName());
	System.out.println("dao넘어온 제목 = " + dto.getTitle());
	System.out.println("dao넘어온 내용 = " + dto.getContent());
	System.out.println("dao넘어온 취미 = " + dto.getHobby());
  }//end 
  
}//class END
