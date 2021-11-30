package com.myspring.springBBS;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.CookieGenerator;

import com.myspring.springBBS.Login.LoginDTO;
import com.myspring.springBBS.Pager.Pager;


@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	@Autowired
	BoardDAO dao;
	
	@Autowired
	private ServletContext  application;
		
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String board_write(Locale locale, Model model) {
		return "boardWrite";
	}//end
	
	@RequestMapping("/insert.do")
	public String board_insert(BoardDTO dto, HttpServletRequest request) {
		logger.info("BoardController");
		String path=application.getRealPath("/resources/upload");
		File dir = new File(path); if (!dir.isDirectory()) { dir.mkdirs(); }
		  System.out.println(path);
		  String img=dto.getUpload_f().getOriginalFilename();
		  File file = new File( path, img);
		    
		try{ 
			dto.getUpload_f().transferTo(file);  
			FileCopyUtils.copy(dto.getUpload_f().getBytes(), file);
			File target=new File(path,img); 
			FileCopyUtils.copy(dto.getUpload_f().getBytes(), target);
		    dto.getUpload_f().transferTo(target);
		}catch(Exception ex){ }		
		dto.setB_file(img); 
		System.out.println("컨트롤 물리적파일="  + dto.getB_file());
		HttpSession session = request.getSession();
		LoginDTO login = (LoginDTO)session.getAttribute("login");
		dto.setId(login.getS_id());
		dao.dbInsert(dto);
		return "redirect:/list.do" ;
  }//end
	
  @RequestMapping("/list.do")
  public ModelAndView board_select(HttpServletRequest request) {
	  ModelAndView mav = new ModelAndView( );	 	  	  
	  String curP = ""; int curPage=1;
	  curP = request.getParameter("curPage");
	  if(curP=="" || curP==null) { curP="1";}
	  	curPage=Integer.parseInt(curP); 
	  
	  String skey="", sval=""; 	  
	  skey=request.getParameter("keyfield");
	  sval=request.getParameter("keyword");
	  if(skey=="" || skey==null || sval=="" || sval==null) {
		  skey="all"; sval="";
	  }	
	  System.out.println("보더컨트롤 skey = " + skey);
	  System.out.println("보더컨트롤 sval = " + sval);
	  int total=dao.dbCountSearch(skey, sval);	
	  
	  
	  Pager p = new Pager(total, curPage, skey, sval);	  
	  List<BoardDTO> list=dao.dbSelect(p);
	  
	  HttpSession session = request.getSession();
	  LoginDTO login = (LoginDTO)session.getAttribute("login");
	  if(login != null) 
		  mav.addObject("session", "ok");
	  else
		  mav.addObject("session", "no");
	  
	  mav.addObject("list", list);  
	  mav.addObject("pager", p);	  
	  mav.setViewName("boardList");
	  return mav;
  }//end
  
  @RequestMapping("/detail.do")
	public ModelAndView board_detail(HttpServletRequest request) {
	  ModelAndView mav = new ModelAndView( );
	  int data=Integer.parseInt(request.getParameter("idx"));
	  String cnt_check = "";
	  cnt_check = request.getParameter("cnt");
	  if(cnt_check!="" && cnt_check!=null)
		  dao.dbCnt(data);
	  BoardDTO dto=dao.dbDetail(data);
	  
	  HttpSession session = request.getSession();
	  LoginDTO login = (LoginDTO)session.getAttribute("login");
	  if(login != null && login.getS_id().equals(dto.getId()))
		  mav.addObject("session", "ok");
	  else
		  mav.addObject("session", "no");
	  
	  mav.addObject("dto", dto);
	  mav.setViewName("boardDetail");
	  return mav;
	}//end
  
  @RequestMapping("/download.do")
	public String board_download(HttpServletRequest request, HttpServletResponse response) {
	 String data="";		
		 try {	
		  String path=application.getRealPath("/resources/upload");	 
		  String filename=URLEncoder.encode(request.getParameter("fidx"), "UTF-8");
		  data=request.getParameter("idx");
		  System.out.println("\n다운로드 filename="+filename+" idx="+data);
		  System.out.println(path);
		 
		  filename=URLEncoder.encode(filename,"UTF-8");
		  response.setHeader("Content-Disposition", "attachment;filename="+filename);
		  File file=new File(path,filename);
		 
			 InputStream is=new FileInputStream(file);
			 OutputStream os=response.getOutputStream();
			 byte[ ] b=new byte[(int)file.length()];
			
			 is.read(b,0,b.length);
			 os.write(b);
			 is.close(); os.close(); 
		  }catch(Exception ex) { }
		  return "redirect:/detail.do?idx="+data;
	}//end
  
  
  @RequestMapping("/edit_req.do")
	public ModelAndView board_edit_req(HttpServletRequest request) {
	  ModelAndView mav = new ModelAndView( );
	  int data=Integer.parseInt(request.getParameter("idx"));
	  BoardDTO dto=dao.dbDetail(data);
	  mav.addObject("dto", dto);
	  mav.setViewName("boardEdit");
	  return mav;
	}//end
  
  @RequestMapping("/edit.do")
	public String board_edit(BoardDTO dto) {  
	  MultipartFile mf=dto.getUpload_f();
	  String path=application.getRealPath("/resources/upload");
	  String img=mf.getOriginalFilename();
	  File file=new File(path, img);
	  try{
	    FileCopyUtils.copy(dto.getUpload_f().getBytes(), file);
	  }catch(Exception ex){ }
	   dto.setB_file(img);	  
	  dao.dbEdit(dto); 
	  return "redirect:/detail.do?idx="+dto.getNo();
	}//end
  
  
  @RequestMapping("/delete.do")
	public ModelAndView board_delete(HttpServletRequest request) {
	  ModelAndView mav = new ModelAndView( );
	  int data=Integer.parseInt(request.getParameter("idx"));
	  dao.dbDelete(data);
	  mav.setViewName("redirect:/list.do");
	  return mav;
	}//end
  
  
  
  @RequestMapping(value = "/album.do")
	public ModelAndView album() {
	    ModelAndView mav = new ModelAndView( );
	    List<BoardDTO> list = dao.dbAlbumList();
	    mav.addObject("list", list);
	    mav.addObject("size", list.size());
		mav.setViewName("album");
		return mav;
	}//end
  
  @RequestMapping(value = "/popup.do")
	public String popup(Locale locale, Model model) {
		return "popup";
	}//end
  
  @ResponseBody
  @RequestMapping(value = "/popup_end.do", method=RequestMethod.POST)
	public void popup_end(HttpServletResponse response, HttpServletRequest request) {
	    System.out.println("팝업 진입체크");
		Cookie setCookie = new Cookie("check", "true");
		setCookie.setMaxAge(60*60*24); // 기간 하루 60*60*24
		setCookie.setPath("/");
		System.out.println("cookie 생성:"+setCookie);			
		response.addCookie(setCookie);
	}//end
  
  @ResponseBody
  @RequestMapping(value = "/popup_check.do", method=RequestMethod.POST)
	public String popup_check(@CookieValue(value="check",required=false) Cookie cookie, HttpServletRequest request) {
	    
	  	if(cookie != null) {
	  		System.out.println("ck:"+cookie.getValue());
	  		return "no";
	  	}else {
	  		return "popup";
	  	}
	}//end
  
}//BoardController class EDN



