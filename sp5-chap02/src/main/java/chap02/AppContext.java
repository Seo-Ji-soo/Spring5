package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   // 해당 클래스를 스프링 설정 클래스로 지정.
public class AppContext {   // 스프링 설정파일.(스프링 = 한 객체를 생성하고 초기화하는 기능)
	
	@Bean // 해당 메서드가 생성한 객체를 스프링이 관리하는 빈 객체로 등록.
	public Greeter greeter() {   // public void 가 아니면 return 값이 필요함.
		                         // @Bean 애노테이션을 붙인 메서드의 이름은 빈 객체를 구분할 때 사용.
		                         // 빈 객체에 대한 정보를 담고 있는 메서드가 greeter()메서드임.
		
		
		Greeter g = new Greeter();
		g.setFormat("%s, 안녕하세요!");   // @Bean 애노테이션을 붙인 메서드는 객체를 생성하고 알맞게 초기화 해야 하므로
		                               // 여기서 지금 Greeter객체를 초기화하고 있음.
		                               // g.setFormat("오른쪽 칸에 입력할것이 문자열형태임을 지정 , 출력할 문자 입력"); 
		return g;
		
		
	}
	
	@Bean
	public Greeter greeter1() {
		Greeter g = new Greeter();
		g.setFormat("안녕하세요, %s님!");
		
		return g;
	}

}
