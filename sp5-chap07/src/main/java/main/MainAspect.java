package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import config.AppCtx;

public class MainAspect {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppCtx.class);
		
		Calculator cal = ctx.getBean("calculator",Calculator.class); // AOP를 적용하지 않았으면 여기서 리턴한 객체는 프록시 객체가 아닌 RecCalculator타입.
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = "+fiveFact);
		//이거 출력결과를 보면 Calculator타입이 RecCalculator 클래스가 아니고 $Proxy17이다.
		//이 타입은 스프링이 생성한 프록시 타입이다.
		System.out.println(cal.getClass().getName()); 
		ctx.close();

	}

}
