package assembler;

import spring.ChangePasswordService;    // 암호 변경 기능 클래스
import spring.MemberDao;
import spring.MemberRegisterService;   // 회원가입 클래스

public class Assembler {  // spring패키지에 있는 회원가입이나 암호 변경 기능을 제공하는 클래스의 객체를 생성하고 
	                      // 의존 대상이 되는 객체를 주입해주는 조립기 클래스 
	
	private MemberDao memberDao; // 회원정보를 Map에 담음.
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	// 의존 객체를 변경하려면 조립기의 코드만 수정하면 됨.
	public Assembler() {
		memberDao = new MemberDao();  // ex)MemberDao 클래스를 상속받은 CachedMemberDao 클래스를 사용해야한다면, 여기서 memberDao = new CachedMemberDao(); 로 수정.
		regSvc = new MemberRegisterService(memberDao);
		pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberRegisterService getMemberRegisterService() {  // MemberRegisterService 객체가 필요한 곳에서 사용.
		return regSvc;
	}

	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}

}
