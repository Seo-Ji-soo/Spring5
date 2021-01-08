package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.MemberPrinter;
import spring.MemberSummaryPrinter;
import spring.VersionPrinter;

@Configuration  // 이 클래스는 Spring 환경설정과 관련된 클래스이다. 라고 말해주는 애노테이션.
//@Component 어노테이션을 붙인 클래스를 스캔해서 스프링 빈으로 등록하려면 설정 클래스(AppCtx)에 @ComponentScan 어노테이션을 적용해야 됨.
@ComponentScan(basePackages= {"spring"}) 
public class AppCtx {     // Assembler클래스를 대신하여 스프링을 사용하는 코드를 작성하는 클래스.
	
	/*
	 "spring" 패키지에 있는 하위 파일들
	 ChangePasswordService, MemberDao, MemberInfoPrinter, MemberListPrinter, MemberRegisterService
	  위의 클래스 파일에 @Component 어노테이션을 붙였다.
	  그래서 설정 파일에서  @ComponentScan(basePackages= {"spring"}) 로 인해 위의 클래스들(@Component 어노테이션을 붙인 클래스)
	  을 스캔하여 스프링 빈으로 등록된다. 
	*/
	
	
	
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
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
		
	}

}
