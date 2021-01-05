package spring;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;  // 시간출력은 맞는데.... LocalDateTime이랑 차이점은.....?
	                                              // 시간을 출력하는 형식??을 말하는 것 같음. 시간 형식 필드생성.
	
	public MemberPrinter() {   // 기본생성자
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");  // dateTimeFormatter값 초기화.--> 시간 출력 형식 지정.
	}
	
	public void print(Member member) {
		
		if(dateTimeFormatter==null) { // dateTimeFormatter필드가 null이면  날짜형식을 %tF로 출력.
			System.out.printf("회원정보 : 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",    // %tF , %ty-%tm-%td : 년월일 표시
					member.getId(),member.getEmail(),member.getName(),member.getRegisterDateTime());  
		}else { // dateTimeFormatter필드가 null이 아니면 dateTimeFormatter을 이용해서 날짜 형식을 맞춰 출력. 
			System.out.printf("회원정보 : 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
					member.getId(),member.getEmail(),member.getName(),dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
	
	
	
	    // @Autowired 어노테이션을 붙인 해당하는 빈이 없으면 익셉션 발생.
		// 따라서 setDateFormatter() 메서드에서 필요로 하는 DateTimeFormatter 타입이 존재하지 않으면 익셉션 발생.
		// 하지만 이 클래스에서는 setDateFormatter() 메서드에 자동 주입할 빈이 존재하지면 익셉션이 발생하기보다는 그냥 dateTimeFormatter 필드가 null이면 됨.
		// dateTimeFormatter 필드가 null이어도 print()메서드를 통해서 알맞게 동작함.
		// 이렇게 자동 주입할 대상이 필수가 아닌 경우, @Autowired 어노테이션의 required속성을 다음과 같이 false로 지정.
		// 이렇게 지정하면 매칭되는 빈이 없어도 익셉션이 발생하지 않음. 또한 setDateFormatter() 메서드를 실행하지 않음.
		@Autowired(required = false) 
		public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
			this. dateTimeFormatter = dateTimeFormatter;
		}

}
