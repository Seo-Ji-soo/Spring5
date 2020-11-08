package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

@Configuration  // 이 클래스는 Spring 환경설정과 관련된 클래스이다. 라고 말해주는 애노테이션.
public class AppCtx {     // Assembler클래스를 대신하여 스프링을 사용하는 코드를 작성하는 클래스.

	@Bean // @configuration설정된 클래스의 메소드에서 사용하는 애노테이션.
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());  //-----?
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}

}
