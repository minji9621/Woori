package com.example.question;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.answer.AnswerForm;
import com.example.user.SiteUser;
import com.example.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor   // 스프링 의존성 주입 규칙에 의해 questionRepository 객체가 자동으로 주입된다.
@Controller
public class QuestionController {
	
	private final QuestionService questionService;
	private final UserService userService;
	
	@GetMapping("/") //  http://localhost:8888/까지만 쓰고 싶을 때는 /만 붙이면 된다.
	public String root() {
		
		// /question/list 해당 URL 요청 메서드로 가라는 의미
		return "redirect:/question/list";

	}
	
	@GetMapping("/list") //  http://localhost:8888/question/list
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Question> paging = this.questionService.getList(page);
		model.addAttribute("paging",paging);
		return "question_list"; // 리턴값으로 templates(템플릿) 안의 question_list 불러온다.
	}
	/*
	paging 객체가 가지고 있는 속성
	paging.isEmpty : 페이지의 존재 여부 의미(게시물이 있으면 false, 없으면 true)
	paging.totalElements : 전체 게시물 개수 의미
	paging.totalPages : 전체 페이지 개수 의미
	paging.size : 페이지 당 보여줄 게시물의 개수 의미
	paging.number : 현재 페이지를 의미
	paging.hasPrevious : 이전 페이지 존재 여부 의미
	paging.hasNext : 다음 페이지 존재 여부 의미
	*/
	
	@GetMapping(value="/detail/{id}")
	public String detail( Model model, @PathVariable("id") Integer id,
							AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String create(QuestionForm questionForm){  //질문 목록에서 질문 등록 버튼을 눌렀을 때 
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create") //질문 폼에서 등록 눌렀을 때
	public String create(@Valid QuestionForm questionform, BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		
		//~질문을 저장하는 코드~
		this.questionService.create(questionform.getSubject(), questionform.getContent(), siteUser);
		
		return "redirect:/question/list"; // 질문 저장 후 질문 목록으로 이동
	}
}
