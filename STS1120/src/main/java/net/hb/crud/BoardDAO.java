package net.hb.crud;

import java.util.List;

public class BoardDAO {
	public BoardDAO() {}
	
	public void dbInsert(BoardDTO dto) {
		System.out.println("\nBoardController board_insert(BoardDTO)");
	}
	
	public List<BoardDTO> dbSelect() {
		List<BoardDTO> list = null;
		return list;
	}
	
	public BoardDTO dbDetail(int code) {
		BoardDTO dto = new BoardDTO();
		return dto;
	}
}
