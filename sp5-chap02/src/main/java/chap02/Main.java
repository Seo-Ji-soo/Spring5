package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;//자바 설정에서 정보를 읽어와 빈 객체를 생성하고 관리.

public class Main { // main() 메서드를 통해 스프링과 Greeter를 실행하는 자바 클래스

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx =   // 객체를 생성할 때 앞서 작성한 AppContext클래스를 생성자 파라미터로 전달.
				new AnnotationConfigApplicationContext(AppContext.class); // AnnotationConfigApplicationContext는 
		                                                                  // AppContext에 정의한 @Bean설정 정보를 읽어와
		                                                                  // Greeter 객체를 생성하고 초기화한다.
		
		// getBean()메서드는 AnnotationConfigApplicationContext가 자바 설정을 읽어와 생성한 빈(bean) 객체를 검색할 때 사용.
		// Greeter g = ctx.getBean("@Bean 애노테이션의 메서드 이름인 빈 객체의 이름", 검색할 빈 객체의 타입); 
		// 앞서 작성한 자바 설정(AppContext)을 보면 @Bean 메서드의 이름이 "greeter" / 생성한 객체의 리턴 타입이 Greeter
		Greeter g= ctx.getBean("greeter", Greeter.class); 
		String msg = g.greet("스프링");
		System.out.println(msg);
		
		Greeter g1 = ctx.getBean("greeter1",Greeter.class);
		String msg1 = g1.greet("스프링");
		System.out.println(msg1);
		ctx.close();
		
	

		
	}

}
