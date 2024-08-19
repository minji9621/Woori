package com.example.question;

import java.time.LocalDateTime;
import java.util.List;

import com.example.answer.Answer;
import com.example.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity //스프링부트가 Question 클래스를 엔티티로 인식한다.
public class Question {
	
	@Id //어노테이션이 붙어있는 변수를 기본키(primary key)로 지정한다.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//데이터를 저장할 때 해당 변수에 값을 일일이 넣지 않아도 1씩 자동 증가해서 저장된다.
	private Integer id;
	
	@Column(length=200) //length : 열(컬럼)의 길이를 지정
	private String subject;
	
	@Column(columnDefinition="TEXT") //columnDefinition : 열(컬럼)의 유형,성격 정의할 때 사용
	private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy="question",cascade = CascadeType.REMOVE)
	private List<Answer> answerList; //답변리스트

	@ManyToOne
	private SiteUser writer;
}

