package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import chap07.Calculator;
import chap07.RecCalculator;
import config.AppCtx;

public class MainAspect {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppCtx.class);
		
		/* 수정 전
		Calculator cal = ctx.getBean("calculator",Calculator.class); // AOP를 적용하지 않았으면 여기서 리턴한 객체는 프록시 객체가 아닌 RecCalculator타입.
		*/
		
		// 수정 후 (import에도 RecCalculator 추가)
		// AppCtx에서 calculator빈을 생성할 때, RecClculator클래스 이므로 문제 없어보이지만 아님. 오류남.
		// 왜? "calculator" 빈의 실제 타입은 Calculator를 상속한 프록시 타입이므로
		// RecCalculator로 타입 변환을 할 수 없기 때문에 익셉션 발생.
		// but, 설정 클래스에서 @EnableAspectJAutoProxy(proxyTargetClass = true)으로 설정을 한다면 아래와 같이(25행) 코드를 작성해도 오류가 안남.
		RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
		
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = "+fiveFact);
		//이거 출력결과를 보면 Calculator타입이 RecCalculator 클래스가 아니고 $Proxy17이다.
		//이 타입은 스프링이 생성한 프록시 타입이다.
		System.out.println(cal.getClass().getName()); 
		ctx.close();

	}

}
