package com.example.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.question.Question;
import com.example.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	private final AnswerRepository answerRepository;
	
	//답변(Answer)을 생성하기 위해 create 메서드 추가
	public void create(Question question, String content, SiteUser writer) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setWriter(writer);
		this.answerRepository.save(answer); //인서트 기능을 하는 메서드인 save() 사용
	}

}
