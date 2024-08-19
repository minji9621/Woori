package com.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor //아래 매개값들을 요구하는 생성자(매개변수가 있는 생성자를 말함)
//@Setter
@Getter
public class HelloLombok {
	private final String hello;
	private final String lombok;
	
	public static void main(String[] args) {
		HelloLombok hl = new HelloLombok("헬로","롬복");
		
		System.out.println(hl.getHello()+", "+hl.getLombok());
	}
	
}
