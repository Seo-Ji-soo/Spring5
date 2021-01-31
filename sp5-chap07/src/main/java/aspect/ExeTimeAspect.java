package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

// 여기에서의 코드는 메서드 실행 전/후(Around Advice)에 사용할 공통기능(Aspect)이다.

@Aspect // 공통 기능을 제공하는 Aspect클래스라고 명시해주는 어노테이션.(프록시 객체) / @Aspect 어노테이션을 적용한 클래스는 Advice와 Pointcut을 함께 제공.
public class ExeTimeAspect {
	
	// @Pointcut : 공통 기능을 적용할 대상을 설정. 
	// execution(public * chap07..*(..)) : chap07 패키지 및 하위 패키지에 있는, 파라미터가 0개 이상인 메서드 호출,'..'은 해당패키지 또는 하위패키지를 표현.
	@Pointcut("execution(public * chap07..*(..))") // chap07 패키지와 그 하위 패키지에 위치한 타입의 public 메서드를 Pointcut으로 설정.
	private void publicTarget() {
		
	}
	
	// @Around : Around Advice를 설정.
	@Around("publicTarget()") //  publicTarget()메서드에 정의한 Pointcut에 공통 기능을 적용한다는 것을 의미.
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{ // ProceedingJoinPoint 타입 파라미터 : 프록시 대상 객체의 메서드를 호출할 때 사용.
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed();// Around Advice에서 사용할 공통 기능 메서드는 대부분 파라미터로 전달받은 proceed() 메서드을 호출해서 사용하여 실제 대상 객체의 메서드를 호출.
			return result;
		}finally {
			long finish = System.nanoTime();
			// joinPoint.getSignature()/ joinPoint.getTarget()/joinPoint.getArgs() : 이 메서드들을 사용해서 대상 객체의 클래스 이름과 메서드 이름을 출력.
			// joinPoint.getSignature():호출한 메서드의 시그너처 = 메서드 이름+파라미터 (메서드 이름,파라미터 타입,개수가 다르면 시그너처가 다르나고 표현)
			Signature sig = joinPoint.getSignature();  // 리턴타입 익센셥 타입은 시그너처에 포함되지 않음.
			System.out.printf("%s.%s(%s)실행 시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(), // joinPoint.getTarget(): 대상 객체.
					sig.getName(),Arrays.toString(joinPoint.getArgs()), // joinPoint.getArgs(): 인자 목록.
					(finish-start));
	
		}
	}
}
