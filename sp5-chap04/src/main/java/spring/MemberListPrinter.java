package spring;

import java.util.Collection;

public class MemberListPrinter {
	
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberListPrinter(MemberDao memberDao1, MemberPrinter printer1) {  // 매개변수가 들어있는 생성자.
		this.memberDao = memberDao1;
		this.printer = printer1;
	}
	
	public void printAll() {   // void = 리턴값 없음. 리턴값 없는 매소드.
		Collection<Member> members = memberDao.selectAll();
		// collection.forEach(변수 -> 반복처리(변수))
		members.forEach(m -> printer.print(m));  // forEach는 배열,List,Map 등에 들어 있는 값을 순서대로 꺼내 처리를 할 때 (for같은 반복문을 처리할 때) 사용.
	}

}
