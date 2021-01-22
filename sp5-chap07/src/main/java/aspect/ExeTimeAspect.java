package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

// 메서드 실행 전/후(Around Advice)에 사용할 공통기능(Aspect)이다.

@Aspect // 공통 기능을 제공하는 Aspect클래스라고 명시해주는 어노테이션.(프록시 객체)
public class ExeTimeAspect {
	
	@Pointcut("execution(public * chap07..*(..))")
	private void publicTarget() {
		
	}
	
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed();
			return result;
		}finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s)실행 시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(),Arrays.toString(joinPoint.getArgs()),
					(finish-start));
		}
		
		
	}

}
