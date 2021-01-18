package spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// 초기화 과정 (연결을 생성할때 필요) - InitializingBean 인터페이스 상속, afterPropertiesSet() 메서드 
// 빈객체 소멸 과정(연결을 끊을때 필요) - DisposableBean 인터페이스 상속, destroy() 메서드

// 두개의 인터페이스를 통해 초기화 메서드와 소멸 메서드가 언제 실행되는지 확인 해 보는 클래스.
public class Client implements InitializingBean, DisposableBean {
	
	private String host;
	

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	// DisposableBean 인터페이스 상속을 받아 생기는 메서드
	public void destroy() throws Exception {
		System.out.println("Client.destroy() 실행");
		
	}
	

	public void send() {
		System.out.println("Client.send()to"+host);
	}


	@Override
	// InitializingBean 인터페이스 상속을 받아 생기는 메서드
	public void afterPropertiesSet() throws Exception {
		System.out.println("Client.afterPropertiesSet() 실행");	
	}
	
}
