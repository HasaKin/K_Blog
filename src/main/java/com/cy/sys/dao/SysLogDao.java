package com.cy.sys.dao;

import com.cy.sys.entity.SysLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface SysLogDao
{
  public abstract int insertObject(SysLog paramSysLog);
  
  public abstract int deleteObjects(@Param("ids") Integer... paramVarArgs);
  
  public abstract List<SysLog> findPageObjects(@Param("username") String paramString, @Param("startIndex") Integer paramInteger1, @Param("pageSize") Integer paramInteger2);
  
  public abstract int getRowCount(@Param("username") String paramString);
}

