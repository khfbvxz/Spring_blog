package net.hb.crud.reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardReplyDAO {
	
	@Autowired
	SqlSessionTemplate temp;
	
	public void dbInsert(BoardReplyDTO dto) {
		temp.insert("board_reply.add", dto);
	} //end
	
	public List<BoardReplyDTO> dbSelect(int idx) {
		List<BoardReplyDTO> list = temp.selectList("board_reply.selectAll", idx);
		return list;
	}//end
	
	public void dbDelete(int data) {
		temp.delete("board_reply.del", data);
	} //end
	
	public BoardReplyDTO dbDetail(int data) {
		return temp.selectOne("board_reply.detail", data);
	}//end
	
	public void dbUpdate(BoardReplyDTO dto) {
		temp.update("board_reply.edit", dto);
	}//end
	
}//BoardReplyDAO class END
