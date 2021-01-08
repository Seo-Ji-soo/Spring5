package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import spring.MemberDao;
import spring.MemberPrinter;
import spring2.ManualBean;


@Configuration
@ComponentScan(basePackages= {"spring"}, 
 excludeFilters = { //excludeFilters 속성을 사용하면 스캔할 때 특정 대상을 자동 등록 대상에서 제외할 수 있다. / 필터가 2개 이상일 때, 배열표시{}
		 @Filter(type = FilterType.ANNOTATION, classes = ManualBean.class), //어노테이션을 붙인 클래스를 스캔 대상에서 제외.
 		 @Filter(type = FilterType.REGEX, pattern = "spring2\\..*") // type 속성값으로 FilterType.REGEX을 줌. spring2패키지에 있는 모든 클래스를 컴포넌트 스캔 대상에서 제외.
       //@Filter(type = FilterType.ASPECTJ, pattern = "spring*Dao") // .ASPECTJ으로 지정.(pom.xml에 모듈 추가해야 됨) spring패키지에서 Dao로 끝나는 클래스를 컴포넌트 스캔 대상에서 제외. 
       //@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MemberDao.class) // 특정 타입이나 그 하위 타입을 컴포넌트 스캔대상에서 제외할때. 
})
public class AppCtxWithExclude {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	

	@Bean
	@Qualifier("printer")   // 해당 빈의 한정 값으로 "printer"을 지정.
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	
}



