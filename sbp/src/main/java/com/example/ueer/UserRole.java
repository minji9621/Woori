package com.example.ueer;

import lombok.Getter;

@Getter
public enum UserRole {	// 사용자가 로그인 한 후 권한과 관련된 내용

	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	UserRole(String value){
		this.value = value;
	}
	
	private String value;
}