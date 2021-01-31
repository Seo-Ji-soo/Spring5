package aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

// 한 Pointcut에 여러 Advice를 적용할 수도 있다. 는걸 보여주는 클래스
@Aspect //공통 기능을 제공하는 Aspect클래스라고 명시해주는 어노테이션.(프록시 객체) / @Aspect 어노테이션을 적용한 클래스는 Advice와 Pointcut을 함께 제공.
public class CacheAspect {

	private Map<Long, Object> cache = new HashMap<>();
	
	@Pointcut("excution(public*chap07..*(long))")
	public void cacheTarget() {
		
	}
	// @Around(): Around Advice를 설정.
	@Around("cacheTarget()")
	public Object execute(ProceedingJoinPoint joinPoint)throws Throwable{
		Long num = (Long)joinPoint.getArgs()[0];
		if(cache.containsKey(num)) {
			System.out.printf("CacheAspect: Cache에서 수함 [%d]\n",num);
			return cache.get(num);
		}
		
		Object result = joinPoint.proceed();
		cache.put(num, result);
		System.out.printf("CacheAspect: Cache에 추가 [%d]\n", num);
		return result;
	}
	
}
