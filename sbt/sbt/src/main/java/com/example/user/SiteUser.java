package com.example.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity //테이블과 1:1 매칭이 되는 사이트
public class SiteUser { //시큐리티에 이미 User 클래스가 있음
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true) //유일한 값만 저장할 수 있다
	private String username;
	private String password;
	
	@Column(unique = true) //테이블 확인 시 : Unique Key(UK)라는 이름으로 보여진다.
	private String email;
	
}
