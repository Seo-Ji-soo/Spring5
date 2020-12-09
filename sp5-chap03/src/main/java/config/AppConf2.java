package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

//@Configuration 어노테이션이 붙은 설정 클래스를 내부적으로 스프링 빈으로 등록.
//다른 빈과 마찬가지로 @Autowired가 붙은 대상에 대해 알맞은 빈을 자동으로 주입.
// 즉, 스프링 컨테이너는 AppConf2 객체를 빈으로 등록. 
// @Autowired가 붙은 두 필드 memberDao, memberPrinter에 해당타입의 빈 객체를 주입. 
@Configuration
public class AppConf2 {
	// @Autowired은 스프링의 자동 주입 기능을 위한 것. 의존 주입과 관련 있음.
	// 이 어노테이션을 붙이면 해당 타입의 빈을 찾아서 필드에 할당.
	// AppConf1클래스에서 MemberDao 타입의 빈을 설정 했으므로
	// 이 클래스의 memberDao 필드에는 AppContf1 클래스에서 설정한 빈이 할당됨.
	@Autowired 
	private MemberDao memberDao;
	@Autowired
	private MemberPrinter memberPrinter;
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao);
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
		return pwdSvc;
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao,memberPrinter);
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		// 세터 메서드를 사용해서 의존 주입을 하지 않아도
		// 스프링 컨테이너가 @Autowired를 붙인 필드에 
		// 자동으로 해당 타입의 빈 객체를 주입
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
