package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;//자바 설정에서 정보를 읽어와 빈 객체를 생성하고 관리.

import config.AppContext;
import spring.Greeter;

// 자세한 설명은 "sp5-chap02"프로젝트 Main.java 파일 참고.

//스프링 컨테이너 클래스. --> 설정 클래스에서 정보를 읽어와 알맞은 빈 객체를 생성하고 각 빈을 연결(의존 주입)하는 작업을 수행.
public class Main { 

	public static void main(String[] args) {
		
		// 1.컨테이너 초기화
		// 초기화가 완료 되면 컨테이너를 사용할 수 있음.
		// 즉, getBean()과 같은 메서드를 이용해서 컨테이너에 보관된 빈 객체를 구한다는 것을 뜻함.
		AnnotationConfigApplicationContext ctx =   
				new AnnotationConfigApplicationContext(AppContext.class); 
		
		// 2.컨테이너에서 빈 객체를 구해서 사용.
		Greeter g= ctx.getBean("greeter", Greeter.class); 
		String msg = g.greet("스프링");
		System.out.println(msg);
		
		// 3. 컨테이너 종료.
		// close()는 AbstractApplicationContext 클래스에 정의 되어 있음.
		// 자바 설정을 사용하는 AnnotationConfigApplicationContext 클래스나 
		// XML설정을 사용하는 GenericXmlApplicationContext 클래스 모두 AbstractApplicationContext 클래스 상속을 받고 있다.
		// 따라서 close()를 이용해서 컨테이너를 종료 할 수 있다.
		ctx.close();
	
	}
}

        // 컨테이너를 초기화 하고 종료할 때는 다음의 작업도 함께 수행.
        // 컨테이너 초기화 - 빈 객체의 생성, 의존 주입, 초기화
        // 컨테이너 종료    - 빈 객체의 소멸 
