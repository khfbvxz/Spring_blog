package net.hb.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value = "/boardWrite.sp", method = RequestMethod.GET)
	public String board_write() {
		logger.info("Welcome home! board_write메소드");
		return "boardWrite";  //WEB-INF/views/boardWrite.jsp문서 가르켜요 
	}//end
	
	@RequestMapping("/boardInsert.sp")
	public String board_insert(BoardDTO dto) { //한건저장
		System.out.println("\nBoardController board_insert(BoardDTO)");
		System.out.println("BoardController넘어온 이름 = "+dto.getName());
		System.out.println("BoardController넘어온 제목 = "+dto.getTitle());
		System.out.println("BoardController넘어온 내용 = "+dto.getContent());
		System.out.println("BoardController넘어온 취미 = "+dto.getHobby());
		//System.out.println("BoardController넘어온 파일 = ");
		return "boardList";
	}//end
	
	@RequestMapping("/boardList.sp")
	public void board_select( ) {//전체출력
		
	}//end
	
	@RequestMapping("/boardDetail.sp")
	public void board_detail( ) { //한건상세
		
	}//end
	
	
	///////////////////////////////////////////////////////////////////////
	@RequestMapping("/boardInsert2.sp")
	public String board_insert2(BoardDTO dto) { //한건저장
		System.out.println("\nBoardController board_insert2(BoardDTO)");
		System.out.println("BoardController넘어온 이름 = "+dto.getName());
		System.out.println("BoardController넘어온 제목 = "+dto.getTitle());
		System.out.println("BoardController넘어온 내용 = "+dto.getContent());
		System.out.println("BoardController넘어온 취미 = "+dto.getHobby());
		return "boardList";
	}//end
	
}//BoardController class END


