package chap07;

// 인터페이스를 구현한 첫번째 클래스는 for문을 이용해서 계승 값을 구했다.
public class ImpeCalculator implements Calculator{

	@Override
	public long factorial(long num) {
		
		long result = 1;
		for(long i =1; i<=num; i++) {
			result *=i;
		}
		
		return result;
	}

}
