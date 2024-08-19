package com.example.user;

import lombok.Getter;

@Getter
public enum UserRole {  //사용자가 로그인 한 후의 권한과 관련된 내용이 되는 enum 클래스
	
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	UserRole(String value){
		this.value = value;
	}
	
	private String value;
}
