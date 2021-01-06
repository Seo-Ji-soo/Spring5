package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.MemberSummaryPrinter;
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
		//return new MemberRegisterService(memberDao());  // MemberRegsiterService 생성자를호출할 때 memberDao()메서드를 호출.
		                                                // 즉, memberDao()가 생성한 객체를 MemberRegisterService 생성자를 통해 주입.
		
		// MemberRegisterService에서 memberDao에다가 자동주입 어노테이션 @Autowired를 붙여 줬기때문에 직접 주입해 줄 필요 없음.
		// 따라서 자동주입전 return new MemberRegisterService(memberDao()); 이부분을 밑에 처럼 작성.
		return new MemberRegisterService();
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		// 이 부분은 setMemberDao메서드를 호출해 빈객체를 주입하지 않아도 스프링이 @Autowired가 붙인 필드에 해당타입의 빈 객체를 찾아서 자동으로 주입.
		//(ChangePasswordService 클래스의 memberDao) 따라서 밑에 부분은 써줄 필요 없음.
		/*pwdSvc.setMemberDao(memberDao());  // 이 메서드는 세터 setMemberDao() 메서드를 이용해서 의존 객체를 주입 */  
		return pwdSvc;
	}
	
	/*// 두 개 이상의 인자를 받는 생성자를 사용하는 설정 추가 (p.82)
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}*/
	
	// @Autowired 어노테이션을 붙인 주입 대상에 일치하는 빈이 두개 이상일때 오류 발생하므로
	// @Qualifier 어노테이션을 붙여 자동 주입할 빈을 지정할 수 있음. 그것을 알아보는 코드를 작성.--> ex) memberPrinter1, memberPrinter2
	@Bean
	@Qualifier("printer")   // 해당 빈의 한정 값으로 "printer"을 지정.
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberPrinter memberPrinter2() {
		// MemberPrinter()에서  MemberSummaryPrinter() 로 바꿔줬음에도 @Qualifier 지정해주기 전이랑 같은 오류가 뜸
		// why???   MemberSummaryPrinter클래스가 MemberPrinter클래스를 상속 받았기 때문. 
		// 즉,  MemberSummaryPrinter 클래스가 MemberPrinter클래스 타입에도 할당할 수 있으므로.
		return new MemberSummaryPrinter();     
	}
	
	
	@Bean
	public MemberListPrinter listPrinter() {
		//return new MemberListPrinter(memberDao(),memberPrinter());
		// MemberListPrinter에서 memberDao, memberPrinter에다가 자동주입 어노테이션 @Autowired를 붙여 줬기때문에 직접 주입해 줄 필요 없음.
		// 따라서 자동주입전 return new MemberListPrinter(memberDao(),memberPrinter()); 이부분을 밑에 처럼 작성.
		return new MemberListPrinter();
	}
	
	/*
	// 자동주입.
	// --> memberPrinter1()를 가져다 씀. 왜냐 MemberInfoPrinter클래스에 setPrinter이  @Qualifier("printer") 이부분으로 자동주입을 지정해 줬기 때문에.
    // MemberInfoPrinter 클래스에서 세터 메서드를 이용해서 의존을 주입하는 설정코드를 추가.(p.86)
	@Bean
	public MemberInfoPrinter infoPrinter() {
		return new MemberInfoPrinter();
	}
	*/
	
	// 명시적 의존 주입.
	// --> 직접적으로 명시를 해줬기 때문에 infoPrinter 클래스에서 setPrinter의  @Qualifier("printer") 지정 부분을 무시하고 
	//      직접 명시한 memberPrinter2 메서드를 호출 할 것 같으나!!!!!!!!! NO!!! 아님!!!!!
	//* 직접 명시한 memberPrinter2()빈(MemberSummaryPrinter타입 객체가) 아닌 memberPrinter1 빈을 사용해서 회원정보를 출력함. 
	//* 즉, 설정 클래스에서 세터 메서드를 통해 명시적으로 의존을 주입해도 
	//* 해당 세터 메서드에 @Autowired어노테이션이 붙어 있으면 자동 주입을 통해 일치하는 빈  @Qualifier("printer")을 주입함. 
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setPrinter(memberPrinter2());  // 여기서 호출한 memberPrinter2()은 MemberSummaryPrinter 객체이므로 이메일과 이름만 출력.
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
