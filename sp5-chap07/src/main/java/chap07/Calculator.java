package chap07;

// 계승을 구하기 위한 인터페이스
public interface Calculator {
	
	// 펙토리얼 구하기. n의 계승은 n!으로 표현하여 ex) 4! = 4*3*2*1 = 24
	public long factorial(long num);

}
