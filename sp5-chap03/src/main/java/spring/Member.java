package spring;

import java.time.LocalDateTime;

public class Member {
	
	private Long id;  // 아이디
	private String email;   // 이메일
	private String password;  // 비밀번호
	private String name;    // 이름
	private LocalDateTime registerDateTime;   // 가입날짜
	
	public Member(String email, String password,
			String name, LocalDateTime regDateTime) {
		this.email = email;   // private 선언한 email를 사용하기 위해서 
	    this.password = password;
	    this.name = name;
	    this.registerDateTime = regDateTime;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}


	public String getName() {
		return name;
	}

	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}


	public void changePassword(String oldPassword, String newPassword) {  // 비밀번호변경 메소드
		if(!password.equals(oldPassword)) // oldPassword가 현재 password와 값이 다르면
			throw new WrongIdPasswordException(); // 예외발생
		this.password = newPassword; // 값이같으면 password 필드를 newPassword 필드로 변경
		
			
	}

}
