package com.example.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.DataNotFoundException;
import com.example.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService { // Service : 컨트롤러와 뷰의 중간역할을 함
	private final QuestionRepository questionRepository;
	
	public Page<Question> getList(int page){
		List<Sort.Order> sort = new ArrayList<>();
		sort.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sort));
		return this.questionRepository.findAll(pageable);
	}
	//게시물 번호 = 전체 게시물 개수 - (현재페이지 * 페이지 당 게시물 개수) - 나열 인덱스
	//게시물 번호 : 최종 게시물에 표시될 게시물의 번호
	//전체 게시물 개수 : 데이터베이스에 저장된 게시물 전체 개수
	//현재페이지 : 페이징에서 현재 선택한 페이지
	//페이지 당 개시물 개수 : 한 페이지에 보여줄 게시물 개수
	//나열 인덱스 : for문 안의 게시물의 순서(나열 인덱스는 현재 페이지에서 표시할 수 있는 게시물의 인덱스)
	
	
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		}else {
			throw new DataNotFoundException("question not found");
		}
	}
	
	public void create(String subject, String content, SiteUser user) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		question.setWriter(user);
		this.questionRepository.save(question);
	} 
}

