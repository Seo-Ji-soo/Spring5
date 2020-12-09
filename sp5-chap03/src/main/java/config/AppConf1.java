package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MemberDao;
import spring.MemberPrinter;

// AppCtx 클래스에서의 빈 설정을 AppConf1, AppConf2의 두개의 클래스로 나눠서 설정.

@Configuration
public class AppConf1 {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}

}
