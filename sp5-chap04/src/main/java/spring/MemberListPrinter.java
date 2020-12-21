package spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberListPrinter {
	
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	/*public MemberListPrinter(MemberDao memberDao1, MemberPrinter printer1) {  // 매개변수가 들어있는 생성자.
		this.memberDao = memberDao1;
		this.printer = printer1;
	}*/
	
	// 밑에 setter메서드에 @Autowired 어노테이션이 붙었으므로 인자가 없는 기본 생성자만 있으면 됨. 왜? MemberDao, MemberPrinter이 자동으로 주입되기 때문에 
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
    
	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}


}
