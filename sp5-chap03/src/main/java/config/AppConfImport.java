package config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.MemberDao;
import spring.MemberPrinter;

// AppCtx 클래스를 AppConf1,AppConf2 나눴던 것 처럼 두 개 이상의 설정 파일을 사용하는 또 다른 방법.
// AppConf1 클래스에 @Import 어노테이션 사용하는 것을 보여주는 클래스.

// AppConfImport 설정 클래스를 사용하면,
// @Import 어노테이션으로 지정한 AppConf2 설정 클래스도 함께 사용.
// 때문에 스프링 컨테이너 설정할때 AppConf2 설정 클래스를 지정할 필요 없음.
// MainForSpring 클래스에서 ctx = new AnnotationConfigApplicationContext(AppConfImport.class)하면 된다는 이야기.

// @Import 사용 2번째,
/*
 *  @Configuration
 *  @Import({AppConf1.class,AppConf2.class})
 *  public class AppConfImport{
 *  
 *  }
 *  
 *  이렇게 배열을 이용해서 두개 이상의 설정 클래스도 지정할 수 있음.
 */


@Configuration
@Import(AppConf2.class) 
public class AppConfImport {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}


}
