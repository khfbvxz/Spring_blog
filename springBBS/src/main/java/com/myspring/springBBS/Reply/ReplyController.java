package com.myspring.springBBS.Reply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.springBBS.Login.LoginDTO;

@Controller
public class ReplyController {

	@Autowired
	ReplyDAO dao;

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

	@RequestMapping(value = "/reply.do", method = RequestMethod.POST)
	public String reply_insert(ReplyDTO dto, HttpServletRequest request) {
		logger.info("ReplyController");
		HttpSession session = request.getSession();
		LoginDTO ldto = (LoginDTO)session.getAttribute("login");
		dto.setR_id(ldto.getS_id());	
		dao.dbInsert(dto);
		return "redirect:/detail.do?idx=" + dto.getR_bno();
	}//end

	
	  @RequestMapping(value="/reply_list.do", method=RequestMethod.GET) 
	  public ModelAndView reply_select(@RequestParam("idx") int idx, HttpServletRequest request) { 
		  ModelAndView mav = new ModelAndView(); 
		  List<ReplyDTO> list = dao.dbSelect(idx);
		  HttpSession session = request.getSession();
		  LoginDTO login = (LoginDTO)session.getAttribute("login");
		  if(login != null)
			  mav.addObject("session", login.getS_id());
		  else
			  mav.addObject("session", "null");
		  mav.addObject("list", list); 
		  mav.setViewName("board_reply"); 
		  return mav; 
	   }//end
	  
	  @RequestMapping(value = "/reply_delete.do", method = RequestMethod.GET)
		public String reply_delete(@RequestParam("r_no") int r_no, @RequestParam("r_bno") int r_bno) {
			dao.dbDelete(r_no);
			System.out.println("reply db delete success");
			return "redirect:/detail.do?idx=" + r_bno;
		}//end
	  
	  @RequestMapping(value = "/reply_edit.do", method = RequestMethod.GET)
		public String reply_edit(@RequestParam("r_no") int r_no, @RequestParam("r_bno") int r_bno, @RequestParam("r_content") String r_content) {
		  System.out.println("no:"+r_no+"/bno:"+r_bno+"/content:"+r_content);	  
		  dao.dbEdit(r_no, r_content);
			System.out.println("reply db edit success");
			return "redirect:/detail.do?idx=" + r_bno;
		}//end
	  
} //ReplyController class END
