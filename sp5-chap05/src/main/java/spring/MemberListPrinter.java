package spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberListPrinter {
	
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	/*public MemberListPrinter(MemberDao memberDao1, MemberPrinter printer1) {  // 매개변수가 들어있는 생성자.
		this.memberDao = memberDao1;
		this.printer = printer1;
	}*/
	
	// 밑에 setter메서드에 @Autowired 어노테이션이 붙었으므로 인자가 없는 기본 생성자만 있으면 됨. 왜? MemberDao, MemberPrinter이 자동으로 주입되기 때문에 
	// 기본생성자 추가 이유 : AppCtx 클래스에서 기본생성자를 이용해서 객체를 생성하기 위함.
	public MemberListPrinter() {  // 기본생성자
		
	}
	
	public void printAll() {   // void = 리턴값 없음. 리턴값 없는 매소드.
		Collection<Member> members = memberDao.selectAll();
		// collection.forEach(변수 -> 반복처리(변수))
		members.forEach(m -> printer.print(m));  // forEach는 배열,List,Map 등에 들어 있는 값을 순서대로 꺼내 처리를 할 때 (for같은 반복문을 처리할 때) 사용.
	}
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
    
	// AppCtx 파일에서 지정한 한정 값(@Qualifier 어노테이션 붙인 후 지정한 이름 "printer")은
	// @Autowired 어노테이션에서 자동 주입할 빈을 한정할 때 사용.
	@Autowired
	@Qualifier("summaryPrinter")  // AppCtx 파일에서 @Qualifier 어노테이션을 붙인 후 한정 값이 "printer"인 빈을 가져다 자동주입 할거다~~~
	public void setPrinter(MemberPrinter printer) {  // @Qualifier을 지우고 (MemberPrinter printer) 이부분 대신에 (MemberSummaryPrinter printer)을 적어줌.
		this.printer = printer;
	}


}
