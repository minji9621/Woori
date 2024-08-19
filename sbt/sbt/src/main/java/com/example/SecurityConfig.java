package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration			//이 클래스가 스프링의 환경 설정 파일임을 의미하는 어노테이션
@EnableWebSecurity		//모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 어노테이션
@EnableMethodSecurity(prePostEnabled=true)  //
public class SecurityConfig { //보안 설정
	
	@Bean	//스프링에 의해 생성 또는 관리되는 객체를 의미
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
				.csrf((csrf) -> csrf
					.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
			.headers((headers -> headers
				.addHeaderWriter(new XFrameOptionsHeaderWriter(
						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))))
			.formLogin((formLogin) -> formLogin
					.loginPage("/user/login")
					.defaultSuccessUrl("/question/list"))
			.logout((logout) -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
					.logoutSuccessUrl("/question/list")
					.invalidateHttpSession(true))
			;
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
