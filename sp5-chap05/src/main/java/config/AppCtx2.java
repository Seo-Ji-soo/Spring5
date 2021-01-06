package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import spring.MemberInfoPrinter2;
import spring.MemberPrinter;

public class AppCtx2 {
	
	// MemberInfoPrinter2에서 @Autowired로 지정한 MemberPrinter에 일치하는 빈이 두개 이상 존재할 경우 (printer(), printer2())
	// MemberInfoPrinter2에서 @Autowired로 지정한 MemberPrinter의 필드 이름 = "printer" 즉, 한정자 이름도 "printer
	// 따라서 여기서 한정자 이름이 "printer" 인 빈을 가져다가 주입한다.

	@Bean
	public MemberPrinter printer() {           // 메소드 이름과 동일한 이름.     한정자: "printer"
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("mprinter")                     //  @Qualifier 어노테이션 값으로 지정된 이름.   한정자: "mprinter"
	public MemberPrinter printer2() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberInfoPrinter2 infoPrinter() {
		MemberInfoPrinter2 infoPrinter = new MemberInfoPrinter2();
		return infoPrinter;
	}
	
	
}
