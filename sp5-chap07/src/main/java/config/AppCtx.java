package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;

@Configuration
//@Aspect 어노테이션을 붙인 클래스를 적용하려면 @EnableAspectJAutoProxy 어노테이션 설정 클래스를 붙여야함.
// 이 어노테이션을 추가하면 스프링은 @Aspect 어노테이션이 붙은 빈 객체를 찾아서 빈 객체의 @Pointcut 설정과 @Around 설정을 사용함.
@EnableAspectJAutoProxy(proxyTargetClass = true) // 빈객체가 인터페이스를 상속할때, 클래스를 이용해서 프록시를 생성. 
public class AppCtx {
	
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}
	
	
	

	
	@Bean // AOP 적용시 RecCalculator가 상속받은 Calculator 인터페이스를 이용해서 프록시 생성.
	public Calculator calculator() {// Calculator 타입이 chap07 패키지에 속함. calculator 빈에 ExeTimeAspect 클래스에 정의한 공통 기능인 measure()를 적용.
		return new RecCalculator();
	}

}
