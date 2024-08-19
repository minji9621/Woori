package com.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
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