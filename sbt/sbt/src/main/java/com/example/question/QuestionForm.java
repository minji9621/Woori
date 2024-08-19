package com.example.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
	
	@NotEmpty(message="제목은 필수 항목입니다.")
	@Size(max=200) //용량 단위는 byte이다.
	private String subject;
	
	@NotEmpty(message="내용은 필수 항목입니다.")
	private String content;
}

/*
	@Size : 문자의 길이를 제한한다.
	@NotNull : Null을 허용하지 않는다.
	@NotEmpty : Null 또는 빈 문자열("")을 허용하지 않는다.
	@Past : 과거 날짜만 입력할 수 있다.
	@Future : 미래 날짜만 입력할 수 있다.
	@FutureOrPresent : 미래 또는 오늘 날짜만 입력할 수 있다.
	@Max : 최댓값 이하의 값만 입력할 수 있다.
	@Min : 최솟값 이상의 값만 입력할 수 있다.
	@Pattern : 입력값을 정규식 패턴으로 검증한다.
*/
