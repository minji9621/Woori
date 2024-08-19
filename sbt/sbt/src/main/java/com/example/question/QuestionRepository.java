package com.example.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
	// JpaRepository< 엔티티클래스명 , 엔티티의 기본키타입 >
	
	//findBy + 엔티티의 속성명(테이블의 컬럼명)이라고 작성하면 JPA가 자동으로 읽어서 실행해준다.
	//JPA가 메서드명을 분석,쿼리 만들고,실행하는 기능이 있다.
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
	Page<Question> findAll(Pageable pageable);
}
