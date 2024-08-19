package com.example.question;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.answer.AnswerForm;
import com.example.ueer.SiteUser;
import com.example.ueer.UserService;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	private final QuestionService questionService;
	private final UserService userService;
	
	@GetMapping("/")		// http://localhost:8888/
	public String root() {
		// /question/list 해당 URL 요청 메서드로 가라
		return "redirect:/question/list";
	}
	
	@GetMapping("/list")	// http://localhost:8888/question/list   
	public String list(Model model,
					@RequestParam(value="page", defaultValue="0") int page) {
		Page<Question> paging = this.questionService.getList(page);
		model.addAttribute("paging", paging);
		return "question_list";
	}
	/*
	paging 객체가 가지고 있는 속성
	paging.isEmpty			: 페이지 존재 여부 의미(있으면 : false / 없으면 : true)
	paging.totalElements	: 전체 게시물 개수 의미.
	paging.totalPages		: 전체 페이지 개수 의미.
	paging.size				: 페이지 당 보여줄 게시물 개수 의미.
	paging.number			: 현재 페이지 의미.
	paging.hasPrevious		: 이전 페이지의 존재 여부 의미.
	paging.hasNext			: 다음 페이지의 존재 여부 의미.
	*/
	
	@GetMapping(value="/detail/{id}")
	public String detail( Model model,
						@PathVariable("id") Integer id,
						AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String create(QuestionForm questionForm) {	// 질문 목록에서 질문 등록 버튼을 눌렀을 때 
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")		// 질문 폼에서 등록 눌렀을 때
	public String create(@Valid QuestionForm questionform,
						BindingResult bindingResult,
						Principal principal) {
		if( bindingResult.hasErrors() ) {
			return "question_form";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.questionService.create(questionform.getSubject(), 
									questionform.getContent(),
									siteUser);
		return "redirect:/question/list";
	}

	@GetMapping("/modify/{id}")
	public String modify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
		Question question = this.questionService.getQuestion(id);
		if(!question.getWriter().getUsername().equals(principal.getName())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다.");
		}
		questionForm.setSubject(question.getSubject());
		questionForm.setContent(question.getContent());
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String modify(@Valid QuestionForm questionForm, BindingResult bindingResult, 
			Principal principal, @PathVariable("id") Integer id) {
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		Question question = this.questionService.getQuestion(id);
		if(!question.getWriter().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다.");
		}
		this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
		return String.format("redirect:/question/detail/%s", id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String delete(Principal principal, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		if(!question.getWriter().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제권한이 없습니다.");
		}
		this.questionService.delete(question);
		return "redirect:/question/list";
	}
}












