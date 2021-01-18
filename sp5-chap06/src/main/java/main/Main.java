package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;//자바 설정에서 정보를 읽어와 빈 객체를 생성하고 관리.
import org.springframework.context.support.AbstractApplicationContext;

import config.AppContext;
import config.AppCtx;
import spring.Client;
import spring.Greeter;

// 자세한 설명은 "sp5-chap02"프로젝트 Main.java 파일 참고.

//스프링 컨테이너 클래스. --> 설정 클래스에서 정보를 읽어와 알맞은 빈 객체를 생성하고 각 빈을 연결(의존 주입)하는 작업을 수행.
//AppCtx를 이용해서 스프링 컨테이너를 생성하고 Client 빈 객체를 구해 사용하는 코드 작성하는 클래스.
public class Main { 

	public static void main(String[] args) throws IOException{
		
		AbstractApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtx.class);
		
		Client client = ctx.getBean(Client.class);
		client.send();
		
		ctx.close();
		
	
	}
}

        // 컨테이너를 초기화 하고 종료할 때는 다음의 작업도 함께 수행.
        // 컨테이너 초기화 - 빈 객체의 생성, 의존 주입, 초기화
        // 컨테이너 종료    - 빈 객체의 소멸 