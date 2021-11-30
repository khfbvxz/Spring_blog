package com.myspring.springBBS;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.myspring.springBBS.Pager.Pager;

@Repository
@Component
public class BoardDAO {
	
	@Autowired
	SqlSessionTemplate temp;
		
	 public void dbInsert(BoardDTO dto) {
		temp.insert("board.add", dto);
		System.out.println("테이블 데이터저장성공\n"); 
	 }//end
 
	 public List<BoardDTO> dbSelect(Pager p){		 
		 List<BoardDTO> list=temp.selectList("board.selectAll", p);
		 return list;
	 }//end
	 
	 public int dbCountSearch(String skey, String sval) {
		 Pager p = new Pager();
		 p.setSkey(skey);
		 p.setSval(sval);
		 System.out.println("dao단 dbCountSearch함수 skey = " + p.getSkey());
		 System.out.println("dao단 dbCountSearch함수 sval = " + p.getSval());
		 int cnt=(Integer)temp.selectOne("board.countSearch", p);
		 return cnt;
	 }//end

	 public BoardDTO dbDetail(int data) {
		 BoardDTO dto=temp.selectOne("board.detail",data);
		 return dto;
	 }//end
	 
	 public void dbCnt(int data) {
		 temp.update("board.cnt", data);
	 }//end
	 
	 public void dbEdit(BoardDTO dto) {
	     temp.update("board.edit",dto);
     }//end
	 	 
	 public void dbDelete(int data) {
		 temp.delete("board.del", data);
	 }//end
	 
	 public List<BoardDTO> dbAlbumList(){		 
		 List<BoardDTO> list=temp.selectList("board.selectAlbum");
		 return list;
	 }//end
	 
}//BoardDAO class EDN
