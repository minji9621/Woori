package com.example.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.question.Question;
import com.example.ueer.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {

	private final AnswerRepository answerRepository;
	// 답변 생성
	public void create(Question question, String content, SiteUser writer) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setQuestion(question);
		answer.setCreateDate(LocalDateTime.now());
		answer.setWriter(writer);
		this.answerRepository.save(answer);
	}
}










