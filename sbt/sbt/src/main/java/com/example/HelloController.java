package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@GetMapping("/hello")  //   http://localhost:8080/hello
	@ResponseBody //메서드가 반환하는 값을 HTTP 응답의 본문으로 직접 반환하도록 지정
	public String hello() {
		return "Hello springboot";
	}
}
