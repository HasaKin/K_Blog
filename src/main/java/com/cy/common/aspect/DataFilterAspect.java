package com.cy.common.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;


@Aspect
@Service
public class DataFilterAspect
{
  @Pointcut("@annotation(com.cy.common.anno.DataFilter)")
  public void doFilterPointCut() {}
  
  @Before("doFilterPointCut()")
  public void beforeMethod(JoinPoint jp)
    throws Throwable
  {
    Object[] args = jp.getArgs();
    System.out.println("datafilter.args=" + Arrays.toString(args));
    if ((args[0] == null) || (((Integer)args[0]).intValue() < 1)) {
      throw new IllegalArgumentException("参数不合法");
    }
  }
}





