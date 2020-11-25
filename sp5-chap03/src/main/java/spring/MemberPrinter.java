package spring;

public class MemberPrinter {
	
	public void print(Member member) {
		System.out.printf("회원정보 : 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",    // %tF , %ty-%tm-%td : 년월일 표시
				member.getId(),member.getEmail(),member.getName(),member.getRegisterDateTime());  
		
	}

}
