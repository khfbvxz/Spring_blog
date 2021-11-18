package net.hb.crud;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/boardWrite.sp", method = RequestMethod.GET)
	public String board_write() {
		logger.info("Welcome home! board_write메소드");
		
		// request.addAttribute("serverTime", formattedDate );
		return "home";
	}
		@RequestMapping("/boardInsert.sp")
		public void board_insert() {
			
		}//end
		
		@RequestMapping("/boardList.sp")
		public void board_select( ) {
			
		}//end
		
		@RequestMapping("/boardDetail.sp")
		public void board_detail( ) {
			
		}//end

	
}
