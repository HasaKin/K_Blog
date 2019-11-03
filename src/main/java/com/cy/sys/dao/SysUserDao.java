package com.cy.sys.dao;

import com.cy.sys.entity.SysUser;
import com.cy.sys.vo.SysUserDeptResult;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface SysUserDao
{
  public abstract int updatePasswordById(@Param("id") Integer paramInteger, @Param("password") String paramString1, @Param("salt") String paramString2);
  
  public abstract int updateById(@Param("id") Integer paramInteger, @Param("email") String paramString1, @Param("mobile") String paramString2);
  
  public abstract SysUser findUserByUserName(String paramString);
  
  public abstract int updateObject(SysUser paramSysUser);
  
  public abstract SysUserDeptResult findObjectById(Integer paramInteger);
  
  public abstract int insertObject(SysUser paramSysUser);
  
  public abstract int validById(@Param("id") Integer paramInteger1, @Param("valid") Integer paramInteger2, @Param("modifiedUser") String paramString);
  
  public abstract List<SysUserDeptResult> findPageObjects(@Param("username") String paramString, @Param("startIndex") Integer paramInteger1, @Param("pageSize") Integer paramInteger2);
  
  public abstract int getRowCount(@Param("username") String paramString);
}

