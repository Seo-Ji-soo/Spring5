package chap07;

// 프록시 객체에 대해서.... 알아보기 위한 코드
// Calculator 인테페이스를 구현. 
// 생성자를 통해 다른 Calculator 객체를 전달받아 delegate 필드에 할당하고 factorial() 메서드에서 delegate.factorial()메서드를 실행.
// 원래 실행 시간을 알아보려면 ImpeCalculator, RecCalculator 클래스 코드를 각각 변경해서 main클래스를 실행시켜야 하지만,
// 두개의 클래스 코드를 변경하지 않고 ExeTimeCalculator(프록시 객체)를 사용하여 main클래스를 실행시켜 두 클래스의 실행시간을 알 수 있다.
public class ExeTimeCalculator implements Calculator {
	
    // 생성자를 통해 다른 Calculator 객체를 전달받아 delegate 필드에 할당.
	private Calculator delegate; // 대리자
	
	// 의존 주입
	public ExeTimeCalculator(Calculator delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public long factorial(long num) {
		long start = System.nanoTime();
		long result = delegate.factorial(num); // factorial() 메서드에서 delegate.factorial()메서드를 실행.
		long end = System.nanoTime();
		// getClass() : 객체가 속하는 클래스의 정보
		// getSimpleName() : 클래스 이름 가져오기
		System.out.printf("%s.factorial(%d) 실행시간 =%d\n", 
				delegate.getClass().getSimpleName(),
				num, (end-start));
		return result;
	}

}
