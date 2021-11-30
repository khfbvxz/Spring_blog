package com.myspring.springBBS.Reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAO {
	
	@Autowired
	SqlSessionTemplate temp;
	
	public void dbInsert(ReplyDTO dto) {
		temp.insert("board_reply.add", dto); 
	}
	
	public List<ReplyDTO> dbSelect(int idx){
		List<ReplyDTO> list=null; 
		list = temp.selectList("board_reply.select", idx); 
		return list;
	}//end
	
	public void dbDelete(int r_no) {
		temp.delete("board_reply.delete", r_no);
	}//end
	
	public void dbEdit(int r_no, String r_content) {
		ReplyDTO dto = new ReplyDTO();
		dto.setR_no(r_no);
		dto.setR_content(r_content);
		temp.update("board_reply.edit", dto);
	}//end
}//class end
