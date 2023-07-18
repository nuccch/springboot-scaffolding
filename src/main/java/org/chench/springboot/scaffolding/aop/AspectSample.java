package org.chench.springboot.scaffolding.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定义切面
 * 使用@Aspect注解定义切面
 *
 * @author chench
 * @desc org.chench.springboot.scaffolding.aop.AspectSample
 * @date 2023.07.17
 */
@Aspect
@Component
public class AspectSample {
    // 定义切点
    // 使用@Pointcut注解定义切点
    // 切点的表达式有多种：
    // 1. execution（匹配方法签名）
    // 2. within（指定所在类或包下）
    // 3. @annotation（方法上有特定的注解）
    // 4. bean()（匹配bean的名子）
    // https://docs.spring.io/spring-framework/reference/core/aop/ataspectj/pointcuts.html#aop-pointcuts-combining

    // 匹配类org.chench.springboot.scaffolding.controller.AOPController的所有方法
    @Pointcut("execution(* org.chench.springboot.scaffolding.controller.AOPController.*(..))")
    // @Pointcut("execution(* org.chench.springboot.scaffolding.controller.*.*(..))")
    // @Pointcut("within(org.chench.springboot.scaffolding.controller..*)")
    // @Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
    // @Pointcut("bean(accountController)")
    public void pointcutSample() {}


    // 定义通知
    // 可以用于定义通知的注解有5个：
    // 1. @Before（前置通知：方法执行前执行）
    // 2. @After（后知通知：无论方法正常返回还是抛出异常都执行）
    // 3. @Around（环绕通知：方法执行前和方法正常返回后执行）
    // 4. @AfterReturning（返回通知：方法正常返回后执行）
    // 5. @AfterThrowing（异常通知：方法抛出异常后执行）

    // 前置通知
    @Before("pointcutSample()")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println(String.format("这里是beforeAdvice执行:%s", new Date()));
    }

    // 后置通知
    @After("pointcutSample()")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println(String.format("这里是afterAdvice执行:%s", new Date()));
    }

    // 环绕通知执行
    // 注意：
    // 1.在环绕通知中一定要返回目标方法的返回值，否则客户端就无法接收到结果啦
    // 2.如果在环绕通知中捕获了目标方法执行时抛出的异常，则异常通知对应的切面逻辑将得不到执行
    @Around("pointcutSample()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println(String.format("这里是aroundAdvice中目标方法执行前:%s", new Date()));
        Object result = proceedingJoinPoint.proceed();
        System.out.println(String.format("这里是aroundAdvice中目标方法执行后:%s", new Date()));
        return result;
    }

    // 返回通知
    // 在返回通知中可以获取目标方法的返回值
    @AfterReturning(value = "pointcutSample()", returning = "r")
    public void afterRunningAdvice(JoinPoint joinPoint, Object r) {
        System.out.println(String.format("这里是afterRunningAdvice执行:%s %s", new Date(), r));
    }

    // 异常通知
    // 有意思的是：
    // 如果在项目中同时使用了@ControllerAdvice注解进行全局异常处理，同时也自定义了异常通知
    // 那么，自定义的异常通知将在@ControllerAdvice之前得到执行
    @AfterThrowing(value = "pointcutSample()", throwing = "e")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable e) {
        System.out.println(String.format("这里是afterThrowingAdvice执行:%s", new Date()));
    }

}
