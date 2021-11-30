package com.myspring.springBBS.Account;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AccountController {
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Inject
	@Autowired
	AccountDAO dao;
	
	@Autowired
	private ServletContext  application;
			
	@RequestMapping(value = "/signUp.do")
	public String signUp(Locale locale, Model model) {
		return "signUp";
  }//end
	
	@ResponseBody
	@RequestMapping("/idCheck.do")
	public String idCheck(HttpServletRequest request) {
		logger.info("AccountController");
		String id = request.getParameter("s_id");
		System.out.println("id:"+id);
		String result = dao.dbCheck(id);
		System.out.println("idcheck result:"+result);
		return result;
	}//end
	
	@RequestMapping("/acct_insert.do")
	public String acct_insert(AccountDTO dto) {
		System.out.println("\nAccountController컨트롤 넘어온 아이디="  + dto.getS_id() );
		System.out.println("AccountController컨트롤 넘어온 패스워드="  + dto.getS_pw());
		System.out.println("AccountController컨트롤 넘어온 이름="  + dto.getS_name());
		System.out.println("AccountController컨트롤 넘어온 성별="  + dto.getS_gender());
		System.out.println("AccountController컨트롤 넘어온 연락처="  + dto.getS_phone());
		System.out.println("AccountController컨트롤 넘어온 메일="  + dto.getS_email());
		
		dao.dbInsert(dto);
		return "redirect:/list.do" ;
  }//end
	
}//class EDN



