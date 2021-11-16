package com.cos.blog3.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.web.PageableDefault;
import com.cos.blog3.config.auth.PrincipalDetail;
import com.cos.blog3.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	

	@GetMapping({"","/"})
	public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) { //  메인페이지로 갈때 데이터를 들고가야함 모델이 필요 
		// 모델은 jsp 리퀘스트 정보 모델에 담으면 뷰까지 데이터 끌고 이동 
		model.addAttribute("boards", boardService.글목록(pageable));
		return "index"; // viewResolver 작동!!
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board" ,boardService.글상세보기(id) );
		return "board/detail";
	}
	@GetMapping("board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
	}
	
	//USER 권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
