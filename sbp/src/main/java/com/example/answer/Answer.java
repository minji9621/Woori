package com.example.answer;

import java.time.LocalDateTime;

import com.example.question.Question;
import com.example.ueer.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	// 질문 엔티티를 참조하기 위해 question 속성(변수) 추가.
	@ManyToOne
	private Question question;
	
	@ManyToOne
	private SiteUser writer;			//작성자
	 
	private LocalDateTime modifyDate;	//수정일시
}

/*
답변 : 질문 = N : 1	-> @ManyToOne
질문 : 답변 = 1 : N	-> @OneToMany
*/










