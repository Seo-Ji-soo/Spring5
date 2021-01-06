package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

// 지정한 이메일을 갖는 Member를 찾아서 정보를 콘솔에 출력하는 클래스.
public class MemberInfoPrinter {
	
	private MemberDao memDao;
	private MemberPrinter printer;
	
	
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if(member == null) {
			System.out.println("데이터 없음");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	
	// 이 두개의 setter 메서드는 MemberDao타입의 객체와 MemberPrinter타입의 객체에 대한 의존을 주입할 때 사용됨.
	// @Autowired은 필드에도 붙일 수 있지만 메서드에도 붙일 수 있음.
	@Autowired    
	public void setMemDao(MemberDao memDao) {
		this.memDao = memDao;
	}
	
	// AppCtx 파일에서 지정한 한정 값(@Qualifier 어노테이션 붙인 후 지정한 이름 "printer")은
	// @Autowired 어노테이션에서 자동 주입할 빈을 한정할 때 사용.
    @Autowired
    @Qualifier("printer")  // AppCtx 파일에서 @Qualifier 어노테이션을 붙인 후 한정 값이 "printer"인 빈을 가져다 자동주입 할거다~~~
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

}
