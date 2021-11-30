package net.hb.crud;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
   
	@Autowired
	SqlSessionTemplate temp; //db쿼리문 id접근 
	
  public void dbInsert(BoardDTO dto) {
	  temp.insert("board.add", dto);
	  System.out.println("dao단에서 저장성공!!!");
  }//end
  
  
  public int  dbCountAllSearch(String skey, String sval) {//조회갯수
	  BoardDTO dto = new BoardDTO();
	  dto.setSkey(skey); //name필드, title필드,  content필드
	  dto.setSval(sval); //검색어
	  int Gtotal = temp.selectOne("board.countAllSearch", dto); 
	  return Gtotal;
  }//end
  
  public List<BoardDTO> dbSelect(int startRow, int endRow, String skey, String sval) { //페이징+검색
	  BoardDTO dto = new BoardDTO();
	  dto.setStartRow(startRow);
	  dto.setEndRow(endRow);
	  dto.setSkey(skey); //name필드, title필드,  content필드
	  dto.setSval(sval); //검색어
	  List<BoardDTO> list = temp.selectList("board.selectAll", dto);
	  return list;
  }//end
  
  
  public List<BoardDTO> dbSelect(int startRow, int endRow) { //페이징만
	  BoardDTO dto = new BoardDTO();
	  dto.setStartRow(startRow);
	  dto.setEndRow(endRow);
	  List<BoardDTO> list = temp.selectList("board.selectAll1126", dto);
	  return list;
  }//end
  
  
  public List<BoardDTO> dbSelect() {//원본
	  List<BoardDTO> list = temp.selectList("board.selectAll1127");
	  return list;
  }//end
  
  
  public int  dbCountAll() { //전체갯수
	  int Gtotal = temp.selectOne("board.countAll"); 
	  return Gtotal;
  }//end
  
  
  public int  dbCountAllSearch2(String skey, String sval) {//조회갯수
	  BoardDTO dto = new BoardDTO();
	  dto.setSkey(skey); //name필드, title필드,  content필드
	  dto.setSval(sval); //검색어
	  int Gtotal = temp.selectOne("board.countAllSearch", dto); 
	  return Gtotal;
  }//end
  
  public BoardDTO dbDetail(int code) {
	  BoardDTO dto = temp.selectOne("board.detail", code);
	  return dto;
  }//end
  
  
  public void dbDelete(int code) {
	  temp.delete("board.del",code); //board.xml문서 <delete  id="del"> delete ~~ where </delete>
  }//end
  
  
  public void dbEdit(BoardDTO dto) {
	 temp.update("board.update", dto); 
  }//end
  
  
  public void dbInsert2(BoardDTO dto) { //복사본
	System.out.println("\nBoardDAO문서 dbInsert2(BoardDTO)");
	System.out.println("dao넘어온 이름 = " + dto.getName());
	System.out.println("dao넘어온 제목 = " + dto.getTitle());
	System.out.println("dao넘어온 내용 = " + dto.getContent());
	System.out.println("dao넘어온 취미 = " + dto.getHobby());
	System.out.println("dao넘어온 파일 = " + dto.getImg_file_name());
  }//end 
}//BoardDAO class END






