package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.Client;
import spring.Client2;

// spring/Client 클래스를 위한 설정 클래스.
@Configuration
public class AppCtx {
	
	@Bean
	public Client client() {
		Client client = new Client();
		client.setHost("host");
		return client;
	}
	
	// 커스텀 메소드
	@Bean(initMethod="connect", destroyMethod="close")
	public Client2 client2() {
		Client2 client = new Client2();
		client.setHost("host2");
		return client;
	}

}
