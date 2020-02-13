package cn.tedu.store.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimerAspect {
    //包名.类名.方法（参数）
    @Around("execution(* cn.tedu.store.service.impl.*.*(..))")
    public Object a(ProceedingJoinPoint pjp) throws Throwable {

        //记录起始时间
        Long start = System.currentTimeMillis();
        //执行方法，获取返回值
        Object object = pjp.proceed();

        Long end = System.currentTimeMillis();
        System.err.println("耗时: " + (end - start) + " 毫秒");
        //返回方法的返回值
        return object;
    }
}
