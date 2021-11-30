package net.hb.crud;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login.sp", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome home!  login메소드"); //프로젝소속.클래스소속.System.out.println출	
		return "home";  //WEB-INF/views폴더/home.jsp문서 가르켜요 
	}
	
}// LoginController class END
