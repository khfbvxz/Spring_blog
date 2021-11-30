package net.hb.crud.reply;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BoardReplyController {

	@Autowired
	BoardReplyDAO rdao;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardReplyController.class);
	
	@RequestMapping(value="/boardreply.sp", method=RequestMethod.GET)
	public String reply_insert(BoardReplyDTO dto) {
		rdao.dbInsert(dto);
		return "redirect:/boardDetail.sp?idx="+dto.getHobby_idx();
	}//end
	
	//boardreply_list
	@RequestMapping(value="/boardreply_list.sp", method=RequestMethod.GET)
	public String reply_select(@RequestParam("idx") int idx, Model model) {
		System.out.println("12345 넘어온댓글 idx = "  + idx);
		//ModelAndView mav = new ModelAndView();
		List<BoardReplyDTO> rLG = rdao.dbSelect(idx);
		model.addAttribute("rLG", rLG);
		return "board_reply";
	}//end
	
	
	@RequestMapping(value="/boardreply_delete.sp", method=RequestMethod.GET)
	public String reply_delete(@RequestParam("Ridx") int Ridx,@RequestParam("idx") int idx ) {
	  rdao.dbDelete(Ridx);
	  return "redirect:/boardDetail.sp?idx="+idx;
	}//end
	
		
	@RequestMapping(value="/boardreply_edit.sp")
	public String reply_preEdit(@RequestParam("rhobby_idx") int rhobby_idx,@RequestParam("rwriter") String rwriter, @RequestParam("rmemo") String rmemo, @RequestParam("hobby_idx") int hobby_idx  ){
		BoardReplyDTO rdto = new BoardReplyDTO();
		rdto.setRhobby_idx(rhobby_idx);
		rdto.setRwriter(rwriter);
		rdto.setRmemo(rmemo);
		rdto.setHobby_idx(hobby_idx);
		System.out.println("컨트롤수정:  "+rdto.getRhobby_idx()+" "+rdto.getRwriter()+" "+rdto.getRmemo()+" "+rdto.getHobby_idx());
		rdao.dbUpdate(rdto);
		return "redirect:/boardDetail.sp?idx=" + rdto.getHobby_idx();
	}//end
	
}//BoardReplyController class END


