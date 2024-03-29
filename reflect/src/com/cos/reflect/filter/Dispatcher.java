package com.cos.reflect.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.reflect.anno.RequestMapping;
import com.cos.reflect.controller.UserController;
import com.cos.reflect.controller.dto.LoginDto;


// 분기 시키기
public class Dispatcher implements Filter {

	private boolean isMatching =false;
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//System.out.println("디스패터 진입 ");
		HttpServletRequest request = (HttpServletRequest) req; 
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//System.out.println("컨텍스트패스 : " + request.getContextPath()); // 프로젝트 시작 주소
		//System.out.println("식별자 주소  : " + request.getRequestURI()); // 프로젝트 시작 주소
		//System.out.println("전체주소  : " + request.getRequestURL()); // 프로젝트 시작 주소
		
		// /user 파싱하기
		String endPoint = request.getRequestURI().replaceAll(request.getContextPath(), "");
		System.out.println("엔드포인트 : " +endPoint);  // /user/login
		
		UserController userController = new UserController();
		
		
		// 리플렉션 -> 메서드를 런타입 시점에서  찾아내서 실행 
		Method[] methods = userController.getClass().getDeclaredMethods(); // 그 파일에 메서드만
		
		for(Method method : methods) { // 4바퀴 join, login , user, hello
			Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
			RequestMapping requestMapping = (RequestMapping) annotation;
			//System.out.println(requestMapping.value());
			
			//LoginDto.class.newInstance(); // 
			//System.out.println(LoginDto.class);
			if(requestMapping.value().equals(endPoint)) {
				isMatching=true;
				try {
					//method.invoke(userController);
					//String path = (String) method.invoke(userController); // 
					
					Parameter[] params = method.getParameters();
					String path = null;
					
					if(params.length != 0 ) {
//						System.out.println("params[0] : "+params[0]);
//						System.out.println("params[0].getName() : "+params[0].getName());
//						System.out.println("params[0].getType() : "+params[0].getType());
						// 해당 dtoInstance를  리플렉션해서 set 함수 호출(username, password)
						Object dtoInstance = params[0].getType().newInstance(); // 원래 for문돌려야된다.
						// user/login => LoginDto, /user/join => JoinDto
//						String username = request.getParameter("username");
//						String password = request.getParameter("password");
//						System.out.println("username" + username);
//						System.out.println("password" + password);
						
//			아래		Enumeration<String> keys =  request.getParameterNames(); //username, password 크기 : 2
						// keys 값을 변형 username = > setUsername;
//						keys 값을 변형 password = > setPassword;
						
						setData(dtoInstance,request);
						path = (String) method.invoke(userController,dtoInstance);
						
						
					}else {
						path = (String) method.invoke(userController);
					}
					
					// path 값 없어서 null 포인트 뜸 
					RequestDispatcher dis = request.getRequestDispatcher(path);// 필터를 다시 안탐  sendredirect  다시 필터 탐 
					dis.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			} 
		}
		if(! isMatching == false) {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("잘못된 주소 요청입니다. 404에러");
			out.flush();
			
		}
//		for( Method method : methods) {
//			//System.out.println(method.getName());
//			if(endPoint.equals("/"+method.getName())) {
//				try {
//					method.invoke(userController);
//				} catch (Exception e) {
//					e.printStackTrace();
//				} 
//			}
//		}
		
//		if(endPoint.equals("/join")){
//			userController.join();
//		} else if(endPoint.equals("/login")) {
//			userController.login();
//		}
	
	}
	
	private <T> void setData(T instance, HttpServletRequest request) {
		Enumeration<String> keys =  request.getParameterNames(); // 크기 2 
		while(keys.hasMoreElements()) {//2번돈다
			String key = (String) keys.nextElement();
			String methodKey = keyToMethodKey(key); //
			Method[] methods =instance.getClass().getDeclaredMethods();// 5개 toString get set
			
			for ( Method method : methods) {
				if(method.getName().equals(methodKey))	{
					try {
						method.invoke(instance, request.getParameter(key));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	private String keyToMethodKey(String key) {
		String firstKey = "set";
		String upperKey = key.substring(0,1).toUpperCase();
		String remainKey = key.substring(1);
		return firstKey+ upperKey + remainKey;
	}
}
