package spring;

import java.time.LocalDateTime;

public class MemberRegisterService {
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail()); // 동일한 이메일을 갖는 회원 데이터가 존재하는지 확인
		                                                         // 존재하면 DuplicateMemberException을 발생.
		if(member != null) { 
			throw new DuplicateMemberException("dup email"+ req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(),req.getPassword(), req.getName(),
				LocalDateTime.now());
		memberDao.insert(newMember); // 같은 이메일을 갖는 회원이 존재하지 않으면 Member 객체를 생성한 뒤 memberDao.insert()메서드를 이용해서 저장.
		return newMember.getId();
	}
}
