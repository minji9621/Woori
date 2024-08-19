package com.example;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.answer.Answer;
import com.example.answer.AnswerRepository;
import com.example.question.Question;
import com.example.question.QuestionRepository;
import com.example.question.QuestionService;

@SpringBootTest //스프링부트를 테스트하는 클래스임을 알려주는 어노테이션
class SbtApplicationTests {
	@Autowired  //의존성 주입(DI->Dependency Injection)
	//스프링이 객체를 대신 생성하여 주입하는 방식
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private QuestionService questionService;
/*	
	//@Test //메서드가 테스트 메서드임을 나타낸다.
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("sbp가 무엇인가요?");         //제목
		q1.setContent("sbp에 대해서 알고 싶습니다.");  //내용
		q1.setCreateDate(LocalDateTime.now());  //작성일시
		this.questionRepository.save(q1);
		//questionRepository가 가지고있는 save() 메서드를 통해
		//question 테이블의 첫번째 행에 들어갈 데이터
		
		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);

	}
	//@Test
	void testJpa2() { //findAll()메서드 사용하는 테스트메서드
		//fineAll() : 해당 테이블에 저장된 모든 데이터를 조회할 때 사용한다. 
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2,all.size());
		//assertEquals(객체에 저장된 갯수,데이터의 사이즈) 
		//=assertEquals(예상결과,실제결과)
		// : Junit에 있는 메서드로 JPA 또는 데이터베이스에서 데이터를 올바르게 가져오는지 확인
		Question q = all.get(0); //get0번 : q1
		assertEquals("sbp가 무엇인가요?",q.getSubject());
	}
	
	//@Test
	void testJpa3() {
		//findById() : id 값으로 데이터를 조회할 때 사용
		//findById(1) : id 값이 1인 질문을 조회
		//Optional : null 값을 유연하게 처리하는 클래스
		//findById() 메서드의 결과가 존재할 수도 존재하지 않을 수도 있기 때문에 리턴타입이 지정
		//.isPresent() : 값이 존재하는지 확인
		Optional<Question> op = this.questionRepository.findById(1);
		if(op.isPresent()) {
			Question q = op.get();
			assertEquals("sbp가 무엇인가요?",q.getSubject());
		}
	}
*/	
	//@Test
	void testJpa4() {
		/*
		 * findBySubject() : 리포지토리가 기본적으로 제공하는 메서드가 아님
		 * QuestionRepository 인터페이스 수정 필요!
		 */
		Question q = this.questionRepository.findBySubject("sbp가 무엇인가요?");
		//assertEquals(5,q.getId());
	}
/*	
	//@Test
	void testJpa5() {
		//*findBySubjectAndContent() 추가
		Question q = this.questionRepository.findBySubjectAndContent("sbp가 무엇인가요?","sbp에 대해서 알고 싶습니다.");
		assertEquals(5,q.getId());
	}
	
	//@Test
	void testJpa6() {
		// findBySubjectLike()
		// : SQL에서 특정 문자열이 포함된 데이터를 찾을 때 Like 사용
		List<Question> qList = this.questionRepository.findBySubjectLike("sbp%");
		Question q = qList.get(0);
		assertEquals("sbp가 무엇인가요?",q.getSubject());
*/		/*
		 * 데이터조회를 위한 조건이 되는 문자열 sbp%
		 * %를 적어줘야 한다.
		 * 위치에 따른 의미
		 * 
		 * sbp%
		 *  : sbp로 시작하는 문자열
		 * %sbp
		 *  : sbp로 끝나는 문자열
		 * %sbp%
		 *  : sbp를 포함하는 문자열
		 */
//	}
	//@Test
	void testJpa7() {  //수정
		Optional<Question> op = this.questionRepository.findById(5);
			assertTrue(op.isPresent());
			Question q = op.get();
			//q.setSubject("수정된 제목");
			this.questionRepository.save(q);
	}
	
	//@Test
	void testJpa8() {  //삭제
		//삭제 전 개수
		assertEquals(2,this.questionRepository.count());
		
		Optional<Question> op = this.questionRepository.findById(5);
		assertTrue(op.isPresent());
		Question q = op.get();
		this.questionRepository.delete(q);
		
		//삭제 후 개수
		assertEquals(1, this.questionRepository.count());
	}
	
	//@Test
	void testJpa9() { //답변 데이터를 저장
		Optional<Question> op = this.questionRepository.findById(6);
		assertTrue(op.isPresent());
		Question q = op.get();
/*		
		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
 */	
	}
	//@Test
	void testJpa10() { //답변의 데이터를 조회 - 답변 엔티티의 question 속성을 이용하여
		 			   //답변(Answer)에 연결된 질문(Question)에 접근
		//findById()를 사용
		Optional<Answer> op = this.answerRepository.findById(1);
		//findById()는 반드시 Optional 클래스로 받아줘야 한다.
		assertTrue(op.isPresent());
		Answer a = op.get();
		//assertEquals(6, a.getQuestion().getId());
	}
	
	//void testJpa10()과 반대
	//@Transactional
	//@Test
	void testJpa11() { //질문 데이터에서 답변 데이터 찾기
		Optional<Question> op = this.questionRepository.findById(6);
		assertTrue(op.isPresent());
		Question q = op.get();
		
		//List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
		/*
		 * Lazy(지연 방식) 
		 * 데이터를 필요한 시점에 가져오는 방식
		 * 
		 * Eager(즉시 방식)
		 * 미리 데이터를 가져오는 방식
		 * 
		 * @OneToMany / @OneToOne
		 * fetch=FetchType.LAZY
		 * fetch=FetchType.EAGER
		 * 
		 * 간단한 해결방법
		 * @Transactional 추가하기
		*/
		
	}
	
	@Test
	void testJpa12() {
		for(int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터:[%03d]",i);
			String content = "내용";
			this.questionService.create(subject,content,null);
		}
	}
}

	
