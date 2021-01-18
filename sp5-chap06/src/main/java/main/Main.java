package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;//자바 설정에서 정보를 읽어와 빈 객체를 생성하고 관리.
import org.springframework.context.support.AbstractApplicationContext;


import config.AppCtx;
import spring.Client;
import spring.Client2;

// 자세한 설명은 "sp5-chap02"프로젝트 Main.java 파일 참고.

// 컨테이너를 초기화 하고 종료할 때는 다음의 작업도 함께 수행.
// 컨테이너 초기화 - 빈 객체의 생성, 의존 주입, 초기화
// 컨테이너 종료    - 빈 객체의 소멸 

//스프링 컨테이너 클래스. --> 설정 클래스에서 정보를 읽어와 알맞은 빈 객체를 생성하고 각 빈을 연결(의존 주입)하는 작업을 수행.
//AppCtx를 이용해서 스프링 컨테이너를 생성하고 Client 빈 객체를 구해 사용하는 코드 작성하는 클래스.
public class Main { 

	public static void main(String[] args) throws IOException{
		
		// 컨테이너 초기화.
		// 설정 클래스에서의 빈 객체들이 실행됨. 
		AbstractApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class);
		
	    // 빈객체 생성. --> send()를 실행 시키기 위해. 
		Client client = ctx.getBean(Client.class);
		client.send();
		
		Client2 client2 = ctx.getBean(Client2.class);
		client2.send();
		
		// 빈객체 소멸. --> Client클래스의 destroy()실행.
		// 스프링 컨테이너 종료. 
		ctx.close();
		// 스프링 컨테이너 종료를 하지 않으면 빈객체 소멸을 하지 않기 때문에 destroy()메소드가 실행되지 않음.
	
	}
}

       