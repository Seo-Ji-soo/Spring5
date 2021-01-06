package spring;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // 설정 클래스(AppCtx)에 빈으로 등록하지 않아도 원하는 클래스를 빈으로 등록해주는 어노테이션.
public class MemberRegisterService {  //이 클래스는 MemberDao 객체의 selectByEmail() 메서드를 이용해서 동일한 이메일을 가진 회원 데이터가 존재하는지 확인.
	
	@Autowired
	private MemberDao memberDao;
	
	/*// 생성자를 통해 의존 객체를 주입 받음.  // public MemberRegisterService = 생성자.  why? 클래스와 이름이 동일한 메서드 이기때문에
	public MemberRegisterService(MemberDao memberDao) {
		// 주입 받은 객체를 필드에 해당.
		this.memberDao = memberDao;
	}*/
	
	// 위에 MemberDao에 @Autowired 어노테이션을 붙여줬음으로 자동으로 주입됨. 따라서 위에 처럼(주석처리 부분)이 아닌 밑에처럼만 적으면 됨.
	// 기본생성자 추가 이유 : AppCtx 클래스에서 기본생성자를 이용해서 객체를 생성하기 위함.
	public MemberRegisterService(){ 
		
	}
	
	public Long regist(RegisterRequest req) {
		// 주입 받은 의존 객체의 메서드를 사용.
		Member member = memberDao.selectByEmail(req.getEmail()); // 동일한 이메일을 갖는 회원 데이터가 존재하는지 확인
		                                                         
		if(member != null) {  // 회원이 널이 아니다 = 이미 회원이 존재한다 
			throw new DuplicateMemberException("dup email"+ req.getEmail()); // 존재하면 DuplicateMemberException을 발생.
		}
		Member newMember = new Member(
				req.getEmail(),req.getPassword(), req.getName(),
				LocalDateTime.now());  
		memberDao.insert(newMember); // 회원 추가메소드. Member 객체를 생성한 뒤 memberDao.insert()메서드를 이용해서 저장.
		return newMember.getId();
	}
}
