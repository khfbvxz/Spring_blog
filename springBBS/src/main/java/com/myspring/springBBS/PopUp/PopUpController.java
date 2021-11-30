package com.myspring.springBBS.PopUp;

import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


public class PopUpController {
	private static final Logger logger = LoggerFactory.getLogger(PopUpController.class);
	
	@Autowired
	private ServletContext  application;
	
	
} //class END
