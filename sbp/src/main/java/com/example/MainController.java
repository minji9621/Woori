package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@GetMapping("/sbp")	// http://localhost:8888/sbp
	@ResponseBody
	public String index() {
		return "sbp에 오신것을 환영합니다.";
	}
}