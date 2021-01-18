package spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// 초기화 과정 (연결을 생성할때 필요) - InitializingBean 인터페이스 상속, afterPropertiesSet() 메서드 
// 빈객체 소멸 과정(연결을 끊을때 필요) - DisposableBean 인터페이스 상속, destroy() 메서드

// 두개의 인터페이스를 통해 초기화 메서드와 소멸 메서드가 언제 실행되는지 확인 해 보는 클래스.
public class Client implements InitializingBean, DisposableBean {
	
	private String host;
	

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
