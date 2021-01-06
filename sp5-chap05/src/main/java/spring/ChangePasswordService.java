package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // 설정 클래스(AppCtx)에 빈으로 등록하지 않아도 원하는 클래스를 빈으로 등록해주는 어노테이션.
public class ChangePasswordService {  //암호 변경 기능 제공
	
	@Autowired   //  이것을 붙이면 설정 클래스(AppCtx)에서 의존을 주입하지 않아도 됨.
	private MemberDao memberDao;
	
	public void changePassword(String email,String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email); // 암호를 변경할 Member 데이터를 찾기 위해 email를 사용함.
		if(member == null) // email에 해당하는 Member가 존재하지 않으면 Exception발생.
			throw new MemberNotFoundException();
		
		member.changePassword(oldPwd, newPwd); //Member이 존재하면 이 메서드를 이용해서 암호 변경 
		
		memberDao.update(member); // 변경된 암호를 보관.
	}

	public void setMemberDao(MemberDao memberDao) {  //setMemberDao() 메서드로 의존하는 MemberDao를 전달받음. 즉,setter를 통해서 의존 객체를 주입받음.
		this.memberDao = memberDao;
	}
}
