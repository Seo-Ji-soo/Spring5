package spring;

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
	public void setMemDao(MemberDao memDao) {
		this.memDao = memDao;
	}
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

}
