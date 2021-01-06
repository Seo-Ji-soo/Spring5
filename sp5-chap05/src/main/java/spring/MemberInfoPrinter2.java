package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter2 {
	
	@Autowired                       // @Qualifier 어노테이션 값으로 지정을 안 해 줬으면, 필드나 파라미터 이름을 한정자로 사용. 
	private MemberPrinter printer;   // 따라서 한정자: "printer"  // 참고 AppCtx2파일
	
}