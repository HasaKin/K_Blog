package com.cy.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.common.anno.RequiresLog;
import com.cy.common.util.IPUtils;
import com.cy.sys.dao.SysLogDao;
import com.cy.sys.entity.SysLog;
import com.cy.sys.entity.SysUser;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@Aspect
public class SysLogAspect
{
  @Autowired
  private SysLogDao sysLogDao;
  
  @Pointcut("@annotation(com.cy.common.anno.RequiresLog)")
  public void doLogPointCut() {}
  
  @Around("doLogPointCut()")
  public Object aroundMethod(ProceedingJoinPoint jp)
    throws Throwable
  {
    long t1 = System.currentTimeMillis();
    
    Object result = jp.proceed();
    
    long t2 = System.currentTimeMillis();
    
    saveObject(jp, t2 - t1);
    return result;
  }
  
  private void saveObject(ProceedingJoinPoint jp, long time) throws Exception
  {
    Class<?> targetCls = jp.getTarget().getClass();
    

    MethodSignature ms = (MethodSignature)
      jp.getSignature();
    Method method = 
    
      targetCls.getDeclaredMethod(
      ms.getName(), 
      ms.getParameterTypes());
    String methodName = targetCls.getName() + "." + method.getName();
    System.out.println(methodName + ".totalTime=" + time);
    
    Object[] args = jp.getArgs();
    System.out.println("args=" + Arrays.toString(args));
    

    RequiresLog requiresLog = 
      (RequiresLog)method.getDeclaredAnnotation(RequiresLog.class);
    
    String operation = requiresLog.value();
    
    SysUser user = (SysUser)
      SecurityUtils.getSubject().getPrincipal();
    String username = user.getUsername();
    
    String ip = IPUtils.getIpAddr();
    
    SysLog log = new SysLog();
    log.setUsername(username);
    log.setOperation(operation);
    log.setMethod(methodName);
    log.setParams(new ObjectMapper().writeValueAsString(args));
    log.setTime(Long.valueOf(time));
    log.setIp(ip);
    log.setCreatedTime(new Date());
    
    this.sysLogDao.insertObject(log);
  }
}





