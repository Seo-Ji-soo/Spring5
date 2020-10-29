package chap02;

public class Greeter { // 콘솔에 간단한 메시지를 출력하는 자바 클래스
	
	private String format;
	
	// format 필드는 setFormat() 메서드로 설정함.
	public String greet(String guest) {
		return String.format(format, guest);
	}
	
	public String greet1(String guest) {
		return String.format(format, guest);
	}
	
	public void setFormat(String format) {
		this.format = format;
	}


	
}
 