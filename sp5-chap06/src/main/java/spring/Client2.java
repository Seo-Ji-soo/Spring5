package spring;

public class Client2 {
	
	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	// 초기화 과정
	public void connect() {
		System.out.println("Client2.connet() 실행");
	}
	
	public void send() {
		System.out.println("Client2.send() to" + host);
	}
	 
	// 소멸 과정
	public void close() {
		System.out.println("Client2.close() 실행");
	}
}
