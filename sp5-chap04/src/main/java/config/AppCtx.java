package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

@Configuration  // 이 클래스는 Spring 환경설정과 관련된 클래스이다. 라고 말해주는 애노테이션.
public class AppCtx {     // Assembler클래스를 대신하여 스프링을 사용하는 코드를 작성하는 클래스.
	
	//스프링 컨테이너는 @Bean이 붙은 메서드에 대해 한 개의 객체만 생성. --> 싱글톤 
	// 다른 설정 메서드에서 memberDao()를 몇 번을 호출하더라도 항상 같은 객체를 리턴한다. 
	//고로, memberRegSvc()메서드와 changePwdSvc()메서드에서 memberDao()메서드를 각각 실행해도 동일한 MemberDao 객체를 사용한다. 

	@Bean // @configuration설정된 클래스의 메소드에서 사용하는 애노테이션.
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		// 스프링 자바 설정에서는 생성자를 이용해서 의존 객체를 주입하기 위해 해당 설정을 담은 메서드를 호출.
		return new MemberRegisterService(memberDao());  // MemberRegsiterService 생성자를호출할 때 memberDao()메서드를 호출.
		                                                // 즉, memberDao()가 생성한 객체를 MemberRegisterService 생성자를 통해 주입.
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		// 이 부분은 setMemberDao메서드를 호출해 빈객체를 주입하지 않아도 스프링이 @Autowired가 붙인 필드에 해당타입의 빈 객체를 찾아서 자동으로 주입.
		//(ChangePasswordService 클래스의 memberDao) 따라서 밑에 부분은 써줄 필요 없음.
		/*pwdSvc.setMemberDao(memberDao());  // 이 메서드는 세터 setMemberDao() 메서드를 이용해서 의존 객체를 주입 */  
		return pwdSvc;
	}
	
	// 두 개 이상의 인자를 받는 생성자를 사용하는 설정 추가 (p.82)
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao(),memberPrinter());
	}
	
	// MemberInfoPrinter 클래스에서 세터 메서드를 이용해서 의존을 주입하는 설정코드를 추가.(p.86)
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemDao(memberDao());
		infoPrinter.setPrinter(memberPrinter());
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
		
	}
	
	

}
