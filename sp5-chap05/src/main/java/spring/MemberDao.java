package spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import spring2.ManualBean;

@ManualBean
@Component  // 설정 클래스(AppCtx)에 빈으로 등록하지 않아도 원하는 클래스를 빈으로 등록해주는 어노테이션.
public class MemberDao {   // 회원정보를 Map에 담음.(아직 디비와 연결 전.)
	
	private static long nextId = 0;
	
	private Map<String, Member> map = new HashMap();
	
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}
	
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
	
	public Collection<Member>selectAll(){   
		return map.values();
	}
	

}
