package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //클래스가 컨트롤러 역할이라는 것을 표시
public class MainController { //  http://localhost:8888/sbt
	@GetMapping("/sbt")
	@ResponseBody //메서드의 결과를 브라우저에서 확인 가능
	public String index() {
		return "sbt에 오신 것을 환영합니다.";
	}
}
