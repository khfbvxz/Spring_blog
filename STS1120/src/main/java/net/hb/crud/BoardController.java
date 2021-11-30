package net.hb.crud;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest; //mvc2=서블릿
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	@Autowired
	ServletContext  application; 
	
	@Autowired
	@Inject
	BoardDAO dao;  
	
	@RequestMapping(value = "/boardWrite.sp", method = RequestMethod.GET)
	public String board_write() {
		logger.info("Welcome home! board_write메소드");
		return "boardWrite";  //WEB-INF/views/boardWrite.jsp문서 
	}//end
	
	
	@RequestMapping("/boardInsert.sp")
	public String board_insert(BoardDTO dto) { 
		String path = application.getRealPath("./resources/upload");
		
		String img = dto.getUpload_f().getOriginalFilename();
	    File file = new File(path, img); 
		try{ dto.getUpload_f().transferTo(file); }catch(Exception ex){  }  
		dto.setImg_file_name(img); 
		
		dao.dbInsert(dto); 
		return "redirect:/boardList.sp"; 
	}//end
	
	
	@RequestMapping("/boardList.sp")
	public String board_select(HttpServletRequest request,Model model) {//전체출력
		//페이징관련변수 
		int startRow,endRow;  //페이지번호 [7]  startRow61 ~ endRow70
		String pnum;  //페이지번호 [7] 문자열형태 pnum기억 
		int pageNUM;  //페이지번호를 정수화 [7정수화] 
		int pageCount, startPage, endPage , tmp ; 
		   
		//검색관련 변수
		String skey="", sval="";
		String returnpage; 
		
		skey = request.getParameter("keyfield");
		sval = request.getParameter("keyword");
		if(skey=="" || sval==""  || skey==null || sval==null || skey.equals("") ) {
			skey = "title";
			sval = "";
		}
		returnpage = "&keyfield=" + skey + "&keyword=" + sval;
		
		pnum  = request.getParameter("pageNum");
		if(pnum=="" || pnum==null || pnum.equals("")) { pnum="1"; }
		pageNUM = Integer.parseInt(pnum);  //문자[7]데이터를 숫자데이터로 변환 
		startRow = (pageNUM-1)*10+1 ; 
		endRow = startRow + 9;
	
	   int Gtotal = dao.dbCountAllSearch(skey, sval);  //조회갯수
	   int GGtotal = dao.dbCountAll(); //총갯수
	   //총페이지갯수 pageCount  270=27페이지 279=28페이지
	   if(Gtotal%10==0) { pageCount = Gtotal/10; }
	   else { pageCount = (Gtotal/10) +1 ;}
	   
	   tmp= (pageNUM-1)%10  ;//8
	   startPage = pageNUM - tmp ;  //19-8
	   endPage = startPage + 9 ;  //11+9
	   if(endPage>pageCount) { endPage=pageCount; }
	   
	   List<BoardDTO> LG = dao.dbSelect(startRow, endRow, skey, sval); //61, 70, 검색필드, 검색어
	   //List<BoardDTO> LG = dao.dbSelect(startRow, endRow); //시작행61, 끝행70
	   //List<BoardDTO> LG = dao.dbSelect(); //페이징없이 전체출력
	   model.addAttribute("LG", LG);
	   model.addAttribute("Gtotal", Gtotal);   //조회갯수
	   model.addAttribute("GGtotal", GGtotal); //전체갯수
	   model.addAttribute("pageCount", pageCount); //총페이지갯수
	   model.addAttribute("pageNUM", pageNUM); //선택하신페이지 숫자화
	   model.addAttribute("startPage", startPage); //시작페이지
	   model.addAttribute("endPage", endPage); //끝페이지
	   model.addAttribute("returnpage", returnpage); //검색후 페이지에 사용
	   return "boardList";
	}//end
	
	
	@RequestMapping("/boardDetail.sp")
	public String  board_detail(@RequestParam("idx") int data, Model model) { //한건상세 idx값을 받아야 하죠 
		//int data = Integer.parseInt(request.getParameter("idx"));
		BoardDTO dto = dao.dbDetail(data);
		model.addAttribute("dto", dto);
		return "boardDetail";
	}//end
	
	
	@RequestMapping("/boardDelete.sp")
	public String board_delete(@RequestParam("idx")  int data) {
		dao.dbDelete(data);
		return "redirect:/boardList.sp" ;
	}//end
	
	
	@RequestMapping("/boardEdit.sp")
	public String board_edit(HttpServletRequest request, Model model) {
		int data = Integer.parseInt(request.getParameter("idx"));
		BoardDTO dto = dao.dbDetail(data);
		model.addAttribute("dto", dto);
		return "boardEdit"; //boardEdit.jsp
	}
	
	
	@RequestMapping("/boardEditSave.sp")
	public String board_edit(BoardDTO dto) { 
		String path = application.getRealPath("./resources/upload");
		
		String img = dto.getUpload_f().getOriginalFilename();
	    File file = new File(path, img); 
		try{ dto.getUpload_f().transferTo(file); }catch(Exception ex){  }  
		dto.setImg_file_name(img); 
		
		//신규저장dao.dbInsert(dto);
		dao.dbEdit(dto); //진짜수정처리
		return "redirect:/boardList.sp"; //boardList.jsp문서대신 바로밑에 있는 컨트롤매핑로이동
	}//end
	
	
	@RequestMapping("/download.sp")
	public void board_download(HttpServletRequest request, HttpServletResponse response) {
	  try {
		  String filename = request.getParameter("fname"); 
		  String path = application.getRealPath("./resources/upload");
		  File file = new File(path, filename) ;
		  
		  response.setHeader("Content-Disposition", "attachment;filename="+filename);
		  InputStream is = new FileInputStream(file) ;  //java.io.InputStream추상클래스 
		  OutputStream os = response.getOutputStream() ;
		  byte[] bt = new byte[(int)file.length()];  
		  
		  is.read(bt, 0, bt.length );
		  os.write(bt);
		  
		  is.close(); os.close(); 
	  }catch (Exception ex) {System.out.println("에러발생: " + ex);}
	}//end
	
	
	
	///////////////////////////////////////////////////////////////////////
	@RequestMapping("/boardInsert2.sp")
	public String board_insert2(BoardDTO dto) { //복사본
		System.out.println("\nBoardController문서 11-23-화요일 1시17분");
		System.out.println("BoardController넘어온 이름 = " + dto.getName());
		System.out.println("BoardController넘어온 제목 = " + dto.getTitle());
		System.out.println("BoardController넘어온 내용 = " + dto.getContent());
		System.out.println("BoardController넘어온 취미 = " + dto.getHobby());
		System.out.println("BoardController넘어온 파일 = " + dto.getUpload_f());
		String path = application.getRealPath("./resources/upload");
		String img = dto.getUpload_f().getOriginalFilename();
	    System.out.println("BoardController넘어온 img = " + img);
	    
	    File file = new File(path, img);
		try{ dto.getUpload_f().transferTo(file); }catch(Exception ex){  }  
		dto.setImg_file_name(img);  
		System.out.println("BoardController넘어온 파일 = " + dto.getImg_file_name());
		//dao.dbInsert(dto);
		return "boardList";
	}//end
	
}//BoardController class END


