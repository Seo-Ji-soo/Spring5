package chap07;

// 인터페이스를 구현한 두번째 클래스는 재귀호출을 이용해서 계승을 구했다.
public class RecCalculator implements Calculator {

	@Override
	public long factorial(long num) {

			if(num==0) 
				return 1;
			else 
				return num*factorial(num-1);
		
	}

}
