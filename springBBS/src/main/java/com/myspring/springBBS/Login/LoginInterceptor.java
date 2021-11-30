package com.myspring.springBBS.Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//public class LoginInterceptor implements HandlerInterceptor {
public class LoginInterceptor extends HandlerInterceptorAdapter{
  private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			HttpSession session = request.getSession();
			
			LoginDTO boxer = null;
			if(session.getAttribute("login") instanceof LoginDTO)
			boxer = (LoginDTO)session.getAttribute("login");
			System.out.println("LoginInterceptor인셉 boxer " + boxer);
			//로그인이 안된 상태에서 요청을 햇을 때 로그인 한 후 요청한 페이지로 이동하도록 하기 위해서 요청한 주소를 세션에 저장한 후 로그인이 성공했을 때 그 주소로 이동하도록 하면 된다.
			String uri = request.getRequestURI();
			System.out.println("LoginInterceptor인셉  uri " + uri);
	        
	          //프로젝트 이름까지 찾아오기때문에 주소만  http://127.0.0.1:8081/order/~~.do
	          int idx = uri.lastIndexOf("/");
			  uri = uri.substring(idx+1);
			
			  System.out.println("LoginInterceptor인셉  uri "+uri);
	           //파라미터 찾아오기  리스트문서 제목클릭하면 idx공유와 같은역할 
			String query = request.getQueryString();
			System.out.println("LoginInterceptor인셉  query=" + query);
			
			//파라미터가 없으면 요청 주소는 그대로 
			if(query==null || query.equals("null")){
				query="";
			}
			else{
				query="?"+query;
			}
			uri = uri + query;
			
			//세션에 uri 저장 
			session.setAttribute("LOGIN",uri);
			boolean adminCheck = false;
			//로그인이 안된 경우 로그인 페이지로 이동
			if(boxer!=null){
				adminCheck=true;	
			}else {
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().append("<script>"
								+ "alert('LoginInterceptor인셉 로그인이필요합니다.');"
								  + "</script>").flush();
				adminCheck=false;
			}
			return adminCheck;
	}//end

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)		throws Exception {
		//컨트롤러의 메소드 끝나고 , 화면처리후 처리 
        logger.info("=======================afterCompletion start========================");
		logger.info("=======================afterCompletion end=========================");

	}//end
			
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)		throws Exception {
		//컨트롤러의 메소드 끝나고 ,1처리 
	    logger.info("=======================postHandle start======================");
		logger.info("=======================postHandle end=========================");	
	}//end

   
}//LoginInterceptor class END
