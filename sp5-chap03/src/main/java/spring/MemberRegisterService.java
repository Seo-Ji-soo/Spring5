package spring;

import java.time.LocalDateTime;

public class MemberRegisterService {
	private MemberDao memberDao;
	
	// 생성자를 토애 의존 객체를 주입 받음.
	public MemberRegisterService(MemberDao memberDao) {
		// 주입 받은 객체를 필드에 해당.
		this.memberDao = memberDao;
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
