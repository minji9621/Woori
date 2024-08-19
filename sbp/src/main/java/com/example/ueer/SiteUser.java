package com.example.ueer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {	// 시큐리티에 이미 User 클래스가 있음.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)	// 유일한 값만 저장할 수 있다.
	private String username;
	
	private String password;
	
	@Column(unique = true)	// 테이블 확인 시 : Unique Key : UK
	private String email;
	
}






