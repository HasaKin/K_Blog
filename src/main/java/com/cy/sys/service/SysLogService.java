package com.cy.sys.service;

import com.cy.common.vo.PageObject;
import com.cy.sys.entity.SysLog;

public abstract interface SysLogService
{
  public abstract int deleteObjects(Integer... paramVarArgs);
  
  public abstract PageObject<SysLog> findPageObjects(String paramString, Integer paramInteger);
}

